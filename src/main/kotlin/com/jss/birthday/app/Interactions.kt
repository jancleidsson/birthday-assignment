package com.jss.birthday.app

import com.jss.birthday.core.interactor.LoadPeople
import com.jss.birthday.core.interactor.TodayBirthday

data class Interactions(
    val todayBirthday: TodayBirthday,
    val loadPeople: LoadPeople
)