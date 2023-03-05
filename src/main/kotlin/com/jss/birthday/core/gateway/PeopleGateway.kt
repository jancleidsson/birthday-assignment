package com.jss.birthday.core.gateway

import com.jss.birthday.core.entity.Person

interface PeopleGateway {
    fun load(path: String): List<Person>
}