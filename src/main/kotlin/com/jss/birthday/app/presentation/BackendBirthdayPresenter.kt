package com.jss.birthday.app.presentation

import com.jss.birthday.app.Interactions
import com.jss.birthday.app.di.InteractionsModule

class BackendBirthdayPresenter(private val interactions: Interactions = InteractionsModule().interactions()) {
    private companion object {
        private const val NO_BIRTHDAY_TEXT = "No people have a birthday today :("
        private const val TODAY_IS_TEXT = "Today is "
        private const val BIRTHDAY_TEXT = " birthday."
        private const val LOAD_FILE_ERROR_TEXT = "Error while load people!"
        private const val PEOPLE_JSON_FILE_PATH = "src/main/resources/peopleList.json"
    }

    fun consoleOutputText(): String {
        val outputText = StringBuilder()
        runCatching {
            interactions.loadPeople(PEOPLE_JSON_FILE_PATH).let { people ->
                interactions.todayBirthday(people).let { peopleBirthday ->
                    if (peopleBirthday.isEmpty()) {
                        outputText.append(NO_BIRTHDAY_TEXT)
                    } else {
                        for (person in peopleBirthday) {
                            outputText.appendLine("$TODAY_IS_TEXT${person.firstName} ${person.lastName}$BIRTHDAY_TEXT")
                        }
                    }
                }
            }
        }.getOrElse {
            outputText.append(LOAD_FILE_ERROR_TEXT)
        }
        return outputText.toString()
    }
}