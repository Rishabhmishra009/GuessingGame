package com.example.guessinggame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.guessinggame.GameFragmentDirections
import com.example.guessinggame.R
import com.example.guessinggame.ResultFragmentArgs
import com.example.guessinggame.databinding.GamefragmentBinding
import com.example.guessinggame.databinding.ResultfragmentBinding

class ResultFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _Binding: ResultfragmentBinding? = null
    val Binding get() = _Binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {

        _Binding = ResultfragmentBinding.inflate(inflater, container, false)
        var view = Binding.root
        Binding.wonLost.text = ResultFragmentArgs.fromBundle(requireArguments()).result
        Binding.neGameButton.setOnClickListener() {
            view.findNavController().navigate(R.id.resultFragment2_to_action_gameFragment)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _Binding = null
    }
}
