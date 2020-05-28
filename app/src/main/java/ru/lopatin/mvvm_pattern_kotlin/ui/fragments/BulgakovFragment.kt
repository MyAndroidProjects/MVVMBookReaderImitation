package ru.lopatin.mvvm_pattern_kotlin.ui.fragments

import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.lopatin.mvvm_pattern_kotlin.R
import ru.lopatin.mvvm_pattern_kotlin.databinding.FragmentBulgakovBinding
import ru.lopatin.mvvm_pattern_kotlin.view_models.BulgakovFragmentViewModel
import ru.lopatin.mvvm_pattern_kotlin.view_models.MainActivityViewModel

class BulgakovFragment : Fragment() {

    companion object {
        @Synchronized
        fun getInstance(): BulgakovFragment {
            return BulgakovFragment()
        }
    }

    private lateinit var viewModel: BulgakovFragmentViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: FragmentBulgakovBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        activity?.let {
            /**
             * если надо общаться междлу двуми ViewModel необходимо передавать активити, к которой принадлежат фрагменты
             * иначе будут созданы разные ViewModel
             */
            viewModel = ViewModelProviders.of(it).get(BulgakovFragmentViewModel::class.java)
            mainActivityViewModel = ViewModelProviders.of(it).get(MainActivityViewModel::class.java)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bulgakov, container, false)
        val myView: View = binding.root
        binding.bulgakovFragmentViewModel = viewModel
        binding.handler = ClickHandler()
        return myView
    }

    override fun onStart() {
        super.onStart()
        Log.d("myLog", "buttonNextPageSelected")
        viewModel.fragmentIsStarting()
    }

    @ExperimentalCoroutinesApi
    override fun onStop() {
        viewModel.fragmentIsStopping()
        super.onStop()
    }

    inner class ClickHandler {
        fun buttonNextPageSelected() {
            Log.d("myLog", "buttonNextPageSelected")
            viewModel.buttonNextPageSelected()
        }

        fun buttonBelyaevSelected() {
            Log.d("myLog", "buttonBelyaevSelected")
            mainActivityViewModel.belyaevButtonSelect()
        }
    }
}