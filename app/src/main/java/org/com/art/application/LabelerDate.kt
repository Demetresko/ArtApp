package org.com.art.application

class LabelerDate {
    private val PLANETS = arrayOf("", "", "", "Реализм", "Импрессионизм", "Пост" + "\n" + "-импрессионизм", "Кубизм", "Экспрессионизм", "Сюрреализм", "Абстракционизм", "Абстрактная" + "\n" + "Живопись", "Поп арт", "", "", "")
    private val PLANETS1 = arrayOf("", "", "", "1850", "1860", "1880", "1910", "1915", "1920", "1930", "1950", "1960", "", "", "")
    var type = 0
    var number: String? = null

    fun getString1(i: Int): String {
        return PLANETS1[i]
    }

    fun getString(i: Int): String {
        return PLANETS[i]
    }

}