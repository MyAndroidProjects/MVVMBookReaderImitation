package ru.lopatin.mvvm_pattern_kotlin.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.util.Log
import kotlinx.coroutines.*
import ru.lopatin.mvvm_pattern_kotlin.repository_gateways.Repository
import ru.lopatin.mvvm_pattern_kotlin.use_cases.ConverterTextToStringWithContract

class BelyaevFragmentViewModel : ViewModel(), ConverterTextToStringWithContract.IConverterContract {
    val showProgressBarFirstMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val showProgressBarSecondMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val pageTextObservableField: ObservableField<String> = ObservableField()

    private var totalPagesNumber: Int = 0
    private var currentPageIndex: Int = 0

    /**
     *  Если сделать job.cancel() что все корутины будут остановлены и уже не запустятся в этом родительском job'е
     *  поэтому в этом фрагменте применена parentJob.cancelChildren()
     *  во фрагменте Bulgakov реализация будет немного другой
     */

    // родительский Job, нужен для контекста и чтобы закончить разом все корутины, дочерние этому job
    private val parentJob = Job()
    // главный поток приложения
    private val dispatcherMain = Dispatchers.Main
    // Контекст корутины
    private val parentCoroutineContext = dispatcherMain + parentJob
    // Область видимости корутины // замена, например, GlobalScope
    private val parentScope = CoroutineScope(parentCoroutineContext)

    init {
        setInitialValues()
    }

    private fun setInitialValues() {
        showProgressBarFirstMutableLiveData.value = false
        showProgressBarSecondMutableLiveData.value = false
        pageTextObservableField.set("")
    }

    fun fragmentIsStarting() {
        getStartPage()
    }

    fun fragmentIsStopping() {
        setInitialValues()
        parentJob.cancelChildren()
    }

    private fun getStartPage() {
        parentScope.launch {
            val getPagesNumberJob = getPagesNumber()
            getPagesNumberJob.join()
            getPageText()
        }
    }

    private suspend fun getPagesNumber() = parentScope.launch {
        // при ошибке получения данный выбрасывает ошибку
        try {
            withContext(Dispatchers.Main) {
                showProgressBarFirstMutableLiveData.value = true
            }
            withContext(Dispatchers.Default) {
                val repository = Repository()
                totalPagesNumber = repository.getProfDoulTextPartsNumber()
            }
        } catch (ex: Exception) {
            Log.d("myLog", "getPagesNumberCoroutine Exception $ex ")
            parentJob.cancelChildren()
        }
    }

    private suspend fun getPageText() {
        // при ошибке получения данных выбрасывает ошибку, поэтому надо оборачивать в try/catch
        parentScope.launch {
            // при ошибке получения данных выбрасывает ошибку, поэтому надо оборачивать в try/catch
            try {
                withContext(Dispatchers.Main) {
                    showProgressBarFirstMutableLiveData.value = true
                    showProgressBarSecondMutableLiveData.value = false
                }
                withContext(Dispatchers.Default) {
                    val repository = Repository()
                    val textPart = repository.getTextPartProfessorDoulByIndex(currentPageIndex)
                    withContext(Dispatchers.Main) {
                        // как вариант переходить в главный поток ... или...
                        showProgressBarFirstMutableLiveData.value = true
                    }
                    showProgressBarSecondMutableLiveData.postValue(true) // ... или использовать postValue
                    ConverterTextToStringWithContract(textPart, this@BelyaevFragmentViewModel)
                }
            } catch (ex: Exception) {
                Log.d("myLog", "getPageText Exception $ex ")
                parentJob.cancelChildren()
            }
        }
    }

    fun buttonNextPageSelected() {
        Log.d("myLog", " buttonNextPageSelected ")
        currentPageIndex++
        if (currentPageIndex >= totalPagesNumber) {
            currentPageIndex = 0
        }
        parentScope.launch {
            getPageText()
        }
    }

    override fun getConvertedString(textString: String) {
        Log.d("myLog", " getConvertedString ")
        showProgressBarFirstMutableLiveData.value = false
        showProgressBarSecondMutableLiveData.value = false
        pageTextObservableField.set(textString)
    }
}