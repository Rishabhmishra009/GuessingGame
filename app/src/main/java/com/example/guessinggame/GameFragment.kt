package com.example.guessinggame

import android.app.GameState
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.databinding.GamefragmentBinding
import com.example.guessinggame.GameViewModel


class GameFragment : Fragment() {
lateinit var viewmodel:GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var tt=e

    }

    private var _Binding: GamefragmentBinding? = null
    val Binding get() = _Binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {
        viewmodel = ViewModelProvider(this)[GameViewModel::class.java]

        _Binding = GamefragmentBinding.inflate(inflater, container, false)
        var view = Binding.root

        updateScreen()
        Binding.guessButton.setOnClickListener() {
            makeGuess(Binding.guess.text.toString().uppercase())
            Binding.guess.text = null
            updateScreen()
            if (isWon() || isLost()) {
                var action =
                    GameFragmentDirections.actionGameFragmentToResultFragment3(WonLostMessage())
                view.findNavController().navigate(action) // used for fragments only and only
                // findNavController is used for activity
            }
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _Binding = null
    }

    fun updateScreen() {
        Binding.word.text = secretWordDisplay
        Binding.lives.text = "you have $livesLeft lives left"
        Binding.incorrectGuesses.text = "incorrect guesses :$incorrectGuess"
    }
}
