package com.jss.birthday.app.presentation

import com.jss.birthday.app.di.InteractionsModule
import com.jss.birthday.app.gateway.BirthdayService
import com.jss.birthday.app.gateway.PeopleService
import com.jss.birthday.core.entity.Person
import com.jss.birthday.core.gateway.BirthdayGateway
import com.jss.birthday.core.gateway.PeopleGateway
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyList
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import java.time.LocalDate

class BackendBirthdayPresenterTest {

    private lateinit var birthdayGateway: BirthdayGateway
    private lateinit var peopleGateway: PeopleGateway
    private lateinit var interactionsModule: InteractionsModule
    private lateinit var subject: BackendBirthdayPresenter

    private val currentDate = LocalDate.now()
    private val people = listOf<Person>(mock(Person::class.java), mock(Person::class.java))
    private val peopleBirthdayIsToday = listOf(
        Person("Jan", "Silva", LocalDate.of(1985, currentDate.month, currentDate.dayOfMonth)),
    )

    @BeforeEach fun setUp() {
        birthdayGateway = mock(BirthdayService::class.java)
        peopleGateway = mock(PeopleService::class.java)
        interactionsModule = InteractionsModule(birthdayGateway, peopleGateway)
        subject = BackendBirthdayPresenter(interactionsModule.interactions())

        `when`(birthdayGateway.todayBirthday(anyList())).thenReturn(peopleBirthdayIsToday)
        `when`(peopleGateway.load(anyString())).thenReturn(people)
    }

    @Test fun `consoleOutputText() - when there are birthdays returns console output with people whose birthday is today`() {
        assertTrue(subject.consoleOutputText().contains("Today is Jan Silva birthday."))
    }

    @Test fun `consoleOutputText() - when there are no birthdays returns console output stating that there are no birthdays today `() {
        `when`(birthdayGateway.todayBirthday(anyList())).thenReturn(listOf())
        assertTrue(subject.consoleOutputText().contains("No people have a birthday today :("))
    }

    @Test fun `consoleOutputText() - when there is an exception on load people file console output should inform the error`() {
        `when`(peopleGateway.load(anyString())).thenThrow(RuntimeException())
        assertTrue(subject.consoleOutputText().contains("Error while load people!"))
    }
}