package com.omsuperg.msranking.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch
import com.omsuperg.msranking.R
import com.omsuperg.msranking.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.serverSelector.setCheckedPosition(0)
        binding.serverSelector.onChangeListener = object : ToggleSwitch.OnChangeListener {
            override fun onToggleSwitchChanged(position: Int) {
                viewModel.setServer(position)
            }
        }
        return binding.root
    }
}