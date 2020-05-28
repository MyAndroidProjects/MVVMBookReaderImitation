package ru.lopatin.mvvm_pattern_kotlin.ui.activities

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import android.util.Log
import ru.lopatin.mvvm_pattern_kotlin.R
import ru.lopatin.mvvm_pattern_kotlin.databinding.ActivityMainBinding
import ru.lopatin.mvvm_pattern_kotlin.ui.fragments.BelyaevFragment
import ru.lopatin.mvvm_pattern_kotlin.ui.fragments.BulgakovFragment
import ru.lopatin.mvvm_pattern_kotlin.view_models.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.mainActivityViewModel = viewModel
        binding.handler = ClickHandler()
    }

    override fun onStart() {
        super.onStart()
        viewModel.activityIsStarting()
        setObservers()
    }

    private fun setObservers() {
        viewModel.currentFragmentLiveData.observe(this, Observer {
            Log.d("myLog", " currentFragmentLiveData.observe $it")
            when (it) {
                MainActivityViewModel.CurrentFragment.BELYAEV -> createBelyaevFragment()
                MainActivityViewModel.CurrentFragment.BULGAKOV -> createBulgakovFragment()
                else -> return@Observer
            }
        })
    }

    private fun createBelyaevFragment() {
        createFragment(BelyaevFragment.getInstance())
    }

    private fun createBulgakovFragment() {
        createFragment(BulgakovFragment.getInstance())
    }

    private fun createFragment(fragment: Fragment) {
        Log.d("myLog", " createFragment fragment:$fragment")
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        Log.d("myLog", " transaction.replace ")
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    inner class ClickHandler {
        fun buttonReadBulgakovOnClick() {
            Log.d("myLog", " buttonReadBulgakovOnClick ")
            viewModel.bulgakovButtonSelect()
        }

        fun buttonReadBelyaevOnClick() {
            Log.d("myLog", " buttonReadBulgakovOnClick ")
            viewModel.belyaevButtonSelect()
        }
    }
}
