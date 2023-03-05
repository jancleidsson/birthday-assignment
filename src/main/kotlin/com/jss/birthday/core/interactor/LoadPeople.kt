package com.jss.birthday.core.interactor

import com.jss.birthday.core.gateway.PeopleGateway

class LoadPeople(private val peopleGateway: PeopleGateway) {
    operator fun invoke(path: String) = peopleGateway.load(path)
}