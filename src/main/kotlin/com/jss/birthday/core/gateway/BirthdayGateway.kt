package com.jss.birthday.core.gateway

import com.jss.birthday.core.entity.Person

interface BirthdayGateway {
    fun todayBirthday(people: List<Person>): List<Person>
}