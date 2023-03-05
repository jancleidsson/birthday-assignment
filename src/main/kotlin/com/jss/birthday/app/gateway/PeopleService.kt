package com.jss.birthday.app.gateway

import com.google.gson.Gson
import com.jss.birthday.core.entity.Person
import com.jss.birthday.core.gateway.PeopleGateway
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PeopleService : PeopleGateway {
    private companion object {
        private const val BIRTHDAY_DATE_PATTERN = "yyyy/MM/dd"
    }

    override fun load(path: String): List<Person> {
        val jsonString = File(path).readText()
        if (jsonString.isBlank()) throw LoadResourceException()
        runCatching {
            return Gson().fromJson(jsonString, Array<Array<String>>::class.java)
                .map { Person(it[0], it[1], LocalDate.parse(it[2], DateTimeFormatter.ofPattern(BIRTHDAY_DATE_PATTERN))) }
        }.getOrElse {
            throw InvalidPeopleJsonException()
        }
    }
}

class LoadResourceException : RuntimeException()
class InvalidPeopleJsonException : RuntimeException()