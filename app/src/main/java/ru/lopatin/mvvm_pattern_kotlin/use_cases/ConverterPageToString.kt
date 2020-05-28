package ru.lopatin.mvvm_pattern_kotlin.use_cases

import ru.lopatin.mvvm_pattern_kotlin.model.BookPage

class ConverterPageToString(private val page: BookPage) {
    fun convert(): String {
        // сложная логика
        return page.text
    }
}