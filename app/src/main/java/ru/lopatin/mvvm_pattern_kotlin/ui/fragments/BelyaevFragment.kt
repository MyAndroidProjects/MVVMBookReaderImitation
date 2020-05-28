package ru.lopatin.mvvm_pattern_kotlin.ui.fragments

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_belyaev.*
import ru.lopatin.mvvm_pattern_kotlin.R
import ru.lopatin.mvvm_pattern_kotlin.databinding.FragmentBelyaevBinding
import ru.lopatin.mvvm_pattern_kotlin.view_models.BelyaevFragmentViewModel
import ru.lopatin.mvvm_pattern_kotlin.view_models.MainActivityViewModel


class BelyaevFragment : Fragment() {
    companion object {
        @Synchronized
        fun getInstance(): BelyaevFragment {
            return BelyaevFragment()
        }
    }

    private lateinit var viewModel: BelyaevFragmentViewModel
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: FragmentBelyaevBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        activity?.let {
            /**
             * если надо общаться междлу двуми ViewModel необходимо передавать активити, к которой принадлежат фрагменты
             * иначе будут созданы разные ViewModel
             */
            viewModel = ViewModelProviders.of(it).get(BelyaevFragmentViewModel::class.java)
            mainActivityViewModel = ViewModelProviders.of(it).get(MainActivityViewModel::class.java)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_belyaev, container, false)
        val myView: View = binding.root
        binding.belyaevFragmentViewModel = viewModel
        binding.handler = ClickHandler()
        return myView
    }

    override fun onStart() {
        super.onStart()
        setObserversShowProgress()
        viewModel.fragmentIsStarting()

    }

    override fun onStop() {
        viewModel.fragmentIsStopping()
        super.onStop()
    }
    private fun setObserversShowProgress() {
        viewModel.showProgressBarFirstMutableLiveData.observe(this, Observer {
            it ?: return@Observer
            if (it) {
                progressBarBelyaev.visibility = View.VISIBLE
            } else {
                progressBarBelyaev.visibility = View.GONE
            }
        })
        viewModel.showProgressBarSecondMutableLiveData.observe(this, Observer {
            it ?: return@Observer
            if (it) {
                progressBarBelyaevSecond.visibility = View.VISIBLE
            } else {
                progressBarBelyaevSecond.visibility = View.GONE
            }
        })
    }

    inner class ClickHandler {
        fun buttonNextPageSelected() {
            viewModel.buttonNextPageSelected()
        }

        fun buttonBulgakovSelected() {
            mainActivityViewModel.bulgakovButtonSelect()
        }
    }
}