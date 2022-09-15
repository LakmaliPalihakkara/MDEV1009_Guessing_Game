package com.mdev.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdev.guessinggame.databinding.FragmentResultBinding
import androidx.navigation.findNavController
import androidx.lifecycle.ViewModelProvider

class ResultFragment : Fragment() {

    private  var _bunding: FragmentResultBinding? = null
    private val binding get() = _bunding!!

    lateinit var viewModel: ResultViewModel
    lateinit var viewModelFactory: ResultViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _bunding = FragmentResultBinding.inflate(inflater,container,false)

        val view = binding.root

        //Use factory to get reference to view model
        var result = ResultFragmentArgs.fromBundle(requireArguments()).result
        viewModelFactory = ResultViewModelFactory(result)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        binding.wonLost.text = viewModel.result

        binding.wonLost.text = ResultFragmentArgs.fromBundle(requireArguments()).result

        binding.newGameButton.setOnClickListener{
            view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _bunding = null
    }
}