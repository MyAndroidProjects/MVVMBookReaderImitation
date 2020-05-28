package ru.lopatin.mvvm_pattern_kotlin.view_models

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.util.Log
import android.view.View
import kotlinx.coroutines.*
import ru.lopatin.mvvm_pattern_kotlin.repository_gateways.Repository
import ru.lopatin.mvvm_pattern_kotlin.use_cases.ConverterTextToStringWithContract
import kotlin.coroutines.CoroutineContext

class BulgakovFragmentViewModel : ViewModel(), ConverterTextToStringWithContract.IConverterContract {
    val showProgressBarFirstObservableField: ObservableField<Int> = ObservableField()
    val showProgressBarSecondObservableField: ObservableField<Int> = ObservableField()
    private var totalPagesNumber: Int = 0
    private var currentPageIndex: Int = 0

    val pageTextObservableField: ObservableField<String> = ObservableField()

    /**
     *  Если сделать job.cancel() что все корутины будут остановлены и уже не запустятся в этом родительском job'е
     *  поэтому в этом фрагменте применена модель повторного создания родительского job'а и сопутствующих контекста и области видимости
     *  во фрагменте Belyaev реализация будет немного другой
     */

    // родительский Job, нужен для контекста и чтобы закончить разом все корутины, дочерние этому job
    private lateinit var parentJob: Job
    // главный поток приложения
    private lateinit var dispatcherMain: MainCoroutineDispatcher
    // Контекст корутины
    private lateinit var parentCoroutineContext: CoroutineContext
    // Область видимости корутины // замена, например, GlobalScope
    private lateinit var parentScope: CoroutineScope
    private val visible: Int = View.VISIBLE
    private val gone: Int = View.GONE

    init {
        showProgressBarFirstObservableField.set(gone)
        showProgressBarSecondObservableField.set(gone)
        pageTextObservableField.set("")
    }

    fun fragmentIsStarting() {
        parentJob = Job()
        dispatcherMain = Dispatchers.Main
        parentCoroutineContext = dispatcherMain + parentJob
        parentScope = CoroutineScope(parentCoroutineContext)
        getPageText()
    }

    @ExperimentalCoroutinesApi
    fun fragmentIsStopping() {
        parentScope.cancel()
        showProgressBarFirstObservableField.set(gone)
        showProgressBarSecondObservableField.set(gone)
        pageTextObservableField.set("")
    }

    private fun getPageText() {
        // при ошибке получения данных выбрасывает ошибку, поэтому надо оборачивать в try/catch
        val getProfDoulCoroutine = parentScope.launch {
            // при ошибке получения данный выбрасывает ошибку
            try {
                withContext(Dispatchers.Main) {
                    showProgressBarFirstObservableField.set(visible)
                    showProgressBarSecondObservableField.set(gone)
                }
                withContext(Dispatchers.Default) {
                    val repository = Repository()
                    totalPagesNumber = repository.getQuantityOfTextPartsMasterAndMargarita()
                    val textPart = repository.getTextPartMasterAndMargaritaByIndex(currentPageIndex)
                    withContext(Dispatchers.Main) {
                        showProgressBarFirstObservableField.set(visible)
                        showProgressBarSecondObservableField.set(visible)
                    }
                    ConverterTextToStringWithContract(textPart, this@BulgakovFragmentViewModel)
                }
            } catch (ex: Exception) {
                Log.d("myLog", "getPageText Exception $ex ")
                parentJob.cancel()
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
            // при ошибке получения данных выбрасывает ошибку, поэтому надо оборачивать в try/catch
            try {
                withContext(Dispatchers.Main) {
                    showProgressBarFirstObservableField.set(visible)
                    showProgressBarSecondObservableField.set(gone)
                }
                withContext(Dispatchers.Default) {
                    val repository = Repository()
                    val textPart = repository.getTextPartMasterAndMargaritaByIndex(currentPageIndex)
                    withContext(Dispatchers.Main) {
                        showProgressBarFirstObservableField.set(visible)
                        showProgressBarSecondObservableField.set(visible)
                    }
                    ConverterTextToStringWithContract(textPart, this@BulgakovFragmentViewModel)
                }
            } catch (ex: Exception) {
                Log.d("myLog", "getPageText Exception $ex ")
                parentJob.cancel()
            }
        }
    }


    override fun getConvertedString(textString: String) {
        Log.d("myLog", " getConvertedString ")
        showProgressBarFirstObservableField.set(gone)
        showProgressBarSecondObservableField.set(gone)
        pageTextObservableField.set(textString)
    }

}