package com.jss.birthday.app.gateway

import com.jss.birthday.core.entity.Person
import com.jss.birthday.core.gateway.BirthdayGateway
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

class BirthdayService(private val currentDate: LocalDate = LocalDate.now()) : BirthdayGateway {

    private companion object {
        const val LAST_FEB_DAY_OF_LEAP_YEAR = 29
        const val LAST_FEB_DAY = 28
    }

    override fun todayBirthday(people: List<Person>) =
        mutableListOf<Person>().let { birthdays ->
            for (person in people) {
                leapYearBirthday(person.birthdayDate).let { fixedBirthday ->
                    if (fixedBirthday == MonthDay.from(currentDate)) {
                        birthdays.add(person)
                    }
                }
            }
            birthdays
        }

    private fun leapYearBirthday(birthday: LocalDate) =
        if (birthday.month == Month.FEBRUARY && birthday.dayOfMonth == LAST_FEB_DAY_OF_LEAP_YEAR && !currentDate.isLeapYear) {
            MonthDay.of(Month.FEBRUARY, LAST_FEB_DAY)
        } else {
            MonthDay.from(birthday)
        }
}