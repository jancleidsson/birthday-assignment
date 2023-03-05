package com.jss.birthday.app.gateway

import com.jss.birthday.core.entity.Person
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.FileNotFoundException
import java.time.LocalDate

class PeopleServiceTest {

    private val subject = PeopleService()
    private val peopleJsonPath = "src/test/resources/testPeopleList.json"
    private val people = listOf(
        Person("Doe", "John", LocalDate.of(1982, 10, 8)),
        Person("Wayne", "Bruce", LocalDate.of(1965, 1, 30)),
        Person("Gaga", "Lady", LocalDate.of(1986, 3, 28)),
        Person("Curry", "Mark", LocalDate.of(1988, 3, 6))
    )

    @Test fun `load() - when there is a valid path returns a list of people`() {
        assertEquals(people, subject.load(peopleJsonPath))
    }

    @Test fun `load() - when there is a invalid path returns an exception`() {
        assertThrows<FileNotFoundException> {
            subject.load("src/test/resources/invalid")
        }
    }

    @Test fun `load() - when there is a empty file returns an exception`() {
        assertThrows<LoadResourceException> {
            subject.load("src/test/resources/testPeopleEmptyList.json")
        }
    }

    @Test fun `load() - when there is an invalid JSON file returns an exception`() {
        assertThrows<InvalidPeopleJsonException> {
            subject.load("src/test/resources/testInvalidPeopleList.json")
        }
    }
}