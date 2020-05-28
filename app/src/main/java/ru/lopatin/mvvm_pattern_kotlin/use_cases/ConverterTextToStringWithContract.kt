package ru.lopatin.mvvm_pattern_kotlin.use_cases

import android.util.Log
import kotlinx.coroutines.*
import ru.lopatin.mvvm_pattern_kotlin.model.BookPage

class ConverterTextToStringWithContract(private val textToConvert: String, stringReceiver: IConverterContract) {

    init {
        /**
         * бизнесс логика приложения сложная конвертация полученного текста
         */
        GlobalScope.launch(Dispatchers.Default) {
            // при ошибке получения данный выбрасывает ошибку
            try {
                val converterTextToPage = ConverterTextToPage(textToConvert)
                val bookPage: BookPage = converterTextToPage.convert()
                val converterPageToString = ConverterPageToString(bookPage)
                // долгие вычисления
                delay(500)
                // меняем контекст на контекст с главным потоком
                withContext(Dispatchers.Main) {
                    stringReceiver.getConvertedString(converterPageToString.convert())
                }
            } catch (ex: Exception) {
                Log.d("myLog", " Exception $ex ")
            }
        }
    }

    interface IConverterContract {
        fun getConvertedString(textString: String)
    }
}