package com.example.guessinggame
import androidx.lifecycle.ViewModel
import com.example.guessinggame.databinding.GamefragmentBinding


class GameViewModel:ViewModel() {
    val word = listOf("Android", "Activity", "Fragment")
    val secretword = word.random().uppercase()
    var secretWordDisplay = ""
    var correctGuess = ""
    var incorrectGuess = ""
    var livesLeft = 8
    init {
        secretWordDisplay = deriveSecretWordDisplay()
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