package ru.lopatin.mvvm_pattern_kotlin.use_cases

import ru.lopatin.mvvm_pattern_kotlin.model.BookPage

class ConverterTextToPage(private val text: String) {
    fun convert(): BookPage {
        // сложное конвертирование
        return BookPage(text)
    }
}