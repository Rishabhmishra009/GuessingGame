package com.example.guessinggame

import android.app.GameState
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.guessinggame.databinding.GamefragmentBinding

class GameFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _Binding: GamefragmentBinding? = null
    val Binding get() = _Binding!!
    val word = listOf("Android", "Activity", "Fragment")
    val secretword = word.random().uppercase()
    var secretWordDisplay = ""
    var correctGuess = ""
    var incorrectGuess = ""
    var livesLeft = 8


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {

        _Binding = GamefragmentBinding.inflate(inflater, container, false)
        var view = Binding.root
        secretWordDisplay = deriveSecretWordDisplay()
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

    fun deriveSecretWordDisplay(): String {
        var display = ""
        secretword.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    fun checkLetter(str: String) = when (correctGuess.contains(str)) {
        true -> str
        false -> ""
    }

    fun makeGuess(guess: String) {
        if (guess.length == 1) {
            if (secretword.contains(guess)) {
                correctGuess += guess
                secretWordDisplay = deriveSecretWordDisplay()
            } else {
                incorrectGuess += guess
                livesLeft--
            }
        }
    }

    private fun isWon() = secretword.equals(secretWordDisplay, true)
    private fun isLost() = livesLeft <= 0
    fun WonLostMessage(): String {
        var message = ""
        if (isWon())
            message = "you WON!"
        else if (isLost())
            message = "you LOST!"
        message += "the word was $secretword"
        return message
    }
}
