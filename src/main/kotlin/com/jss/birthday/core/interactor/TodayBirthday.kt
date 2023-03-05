package com.jss.birthday.core.interactor

import com.jss.birthday.core.entity.Person
import com.jss.birthday.core.gateway.BirthdayGateway

class TodayBirthday(private val birthdayGateway: BirthdayGateway) {
    operator fun invoke(people: List<Person>) = birthdayGateway.todayBirthday(people)
}