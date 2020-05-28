package ru.lopatin.mvvm_pattern_kotlin.repository_gateways

import kotlinx.coroutines.*
import ru.lopatin.mvvm_pattern_kotlin.storage.StorageImitation

class Repository  {


    suspend  fun getQuantityOfTextPartsMasterAndMargarita(): Int {
        // запрос к базе данных
        val storageImitation = StorageImitation()
        delay(500)
        return storageImitation.masterAndMargarita.size
    }

    suspend fun getTextPartMasterAndMargaritaByIndex(index: Int): String {
        // запрос к базе данных
        val storageImitation = StorageImitation()
        delay(1000)
        return storageImitation.masterAndMargarita[index]

    }

     suspend fun getProfDoulTextPartsNumber(): Int {
        val storageImitation2 = StorageImitation()
        delay(500)
        return storageImitation2.professorDoul.size
    }

     suspend fun getTextPartProfessorDoulByIndex(index: Int): String {
        val storageImitation = StorageImitation()
        delay(1000)
        return storageImitation.professorDoul[index]
    }
}