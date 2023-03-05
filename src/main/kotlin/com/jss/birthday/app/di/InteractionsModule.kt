package com.jss.birthday.app.di

import com.jss.birthday.app.Interactions
import com.jss.birthday.app.gateway.BirthdayService
import com.jss.birthday.app.gateway.PeopleService
import com.jss.birthday.core.gateway.BirthdayGateway
import com.jss.birthday.core.gateway.PeopleGateway
import com.jss.birthday.core.interactor.LoadPeople
import com.jss.birthday.core.interactor.TodayBirthday

data class InteractionsModule(
    private val birthdayGateway: BirthdayGateway = BirthdayService(),
    private val peopleGateway: PeopleGateway = PeopleService()
) {
    fun interactions() = Interactions(TodayBirthday(birthdayGateway), LoadPeople(peopleGateway))
}