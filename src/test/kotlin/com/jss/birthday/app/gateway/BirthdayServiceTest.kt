package com.jss.birthday.app.gateway

import com.jss.birthday.core.entity.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class BirthdayServiceTest {

    private val subject = BirthdayService()
    private val currentDate = LocalDate.now()
    private val peopleBirthdayIsToday = listOf(
        Person("Jan", "Silva",  LocalDate.of(1985, currentDate.month, currentDate.dayOfMonth)),
        Person("Ana", "Nova", LocalDate.of(1987, currentDate.month, currentDate.dayOfMonth))
    )
    private val peopleBirthdayIsNotToday = listOf(
        Person("Doe", "John", currentDate.minusDays(1)),
        Person("Wayne", "Bruce", LocalDate.of(1965, currentDate.month, currentDate.plusDays(1).dayOfMonth)),
        Person("Gaga", "Lady", LocalDate.of(1986, currentDate.plusMonths(1).month, currentDate.dayOfMonth)),
    )
    private val peopleFebruary29thBirthday = listOf(
        Person("Carlos", "Soares",  LocalDate.of(2020, 2, 29)),
        Person("Nuno", "Gois",  LocalDate.of(2016, 2, 29))
    )
    private val peopleBirthday = peopleBirthdayIsToday + peopleBirthdayIsNotToday + peopleFebruary29thBirthday

    @Test fun `todayBirthday() - returns a list of people whose birthday is today`() {
        assertEquals(peopleBirthdayIsToday, subject.todayBirthday(peopleBirthday))
    }

    @Test fun `todayBirthday() - returns a empty list when there are any birthday today`() {
        assertEquals(listOf<Person>(), subject.todayBirthday(peopleBirthdayIsNotToday))
    }

    @Test fun `todayBirthday() - returns a list of people whose birthday is on February 28th when they birthday are February 29th and is not a leap year`() {
        val subject = BirthdayService(currentDate = LocalDate.of(2021, 2, 28))
        assertEquals(peopleFebruary29thBirthday, subject.todayBirthday(peopleBirthday))
    }

    @Test fun `todayBirthday() - returns a list of people whose birthday is on February 29th when they birthday are February 29th and is a leap year`() {
        val subject = BirthdayService(currentDate = LocalDate.of(2020, 2, 29))
        assertEquals(peopleFebruary29thBirthday, subject.todayBirthday(peopleBirthday))
    }
}