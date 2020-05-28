package ru.lopatin.mvvm_pattern_kotlin.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.databinding.ObservableField
import android.util.Log
import ru.lopatin.mvvm_pattern_kotlin.R

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val currentFragmentLiveData: MutableLiveData<CurrentFragment> = MutableLiveData()
    val writerSurname: ObservableField<String> = ObservableField()
    val writerName: ObservableField<String> = ObservableField()
    val writerPatronymic: ObservableField<String> = ObservableField()
    val showReadBulgakovButtonHideBelyaevButtonLiveData: ObservableField<Boolean> = ObservableField()

    private val app: Application = application

    init {
        writerSurname.set("")
        writerName.set("")
        writerPatronymic.set("")
        showReadBulgakovButtonHideBelyaevButtonLiveData.set(true)
    }

    enum class CurrentFragment {
        BULGAKOV,
        BELYAEV
    }

    fun activityIsStarting() {
        currentFragmentLiveData.value = CurrentFragment.BULGAKOV
        setWriter()
    }

    private fun setWriter() {
        when (currentFragmentLiveData.value) {
            CurrentFragment.BULGAKOV -> {
                writerSurname.set(app.resources.getString(R.string.bulgakov_surname))
                writerName.set(app.resources.getString(R.string.bulgakov_name))
                writerPatronymic.set(app.resources.getString(R.string.bulgakov_patronymic))
                showReadBulgakovButtonHideBelyaevButtonLiveData.set(false)
            }
            CurrentFragment.BELYAEV -> {
                writerSurname.set(app.resources.getString(R.string.belyaev_surname))
                writerName.set(app.resources.getString(R.string.belyaev_name))
                writerPatronymic.set(app.resources.getString(R.string.belyaev_patronymic))
                showReadBulgakovButtonHideBelyaevButtonLiveData.set(true)
            }
        }
    }

   fun bulgakovButtonSelect() {
       Log.d("myLog", " MainActivityViewModel bulgakovButtonSelect ")
        currentFragmentLiveData.value = CurrentFragment.BULGAKOV
        setWriter()
    }

    fun belyaevButtonSelect() {
        Log.d("myLog", " MainActivityViewModel belyaevButtonSelect ")
        currentFragmentLiveData.value = CurrentFragment.BELYAEV
        setWriter()
    }
}