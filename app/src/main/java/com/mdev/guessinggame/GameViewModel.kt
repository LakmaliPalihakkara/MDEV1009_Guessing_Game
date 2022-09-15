package com.mdev.guessinggame

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    // Game Variables
    val words = listOf("Android", "Activity", "Georgian") // List of available words
    var secretWord = words.random().uppercase() //Randomly picked word the user has to guess
    var secretWordDisplay = "" // How the word is display
    var correctGuesses = "" // Correct letters
    var incorrectGuesses = "" // Incorrect letters
    var livesLeft = 8 // Number of lives left

    init{
        secretWordDisplay = deriveSecretWordDisplay()
    }

    // Derive secret word display
    fun deriveSecretWordDisplay() : String
    {
        var display = ""

        secretWord.forEach {
            display += checkLetter(it.toString())

        }

        return display
    }

    //Check letter
    fun checkLetter(str: String) = when(correctGuesses.contains(str))
    {
        true -> str
        false -> "_"
    }

    //Make guess
    fun makeGuess(guess:String)
    {
        if(guess.length == 1)
        {
            if(secretWord.contains(guess))
            {
                correctGuesses += guess
                secretWordDisplay = deriveSecretWordDisplay()
            } else
            {
                incorrectGuesses += "$guess"
                livesLeft --
            }
        }
    }

    //Check win
    fun isWon() = secretWord.equals(secretWordDisplay, true)

    //Check lost
    fun isLost() = livesLeft <= 0

    // Display win/lose message
    fun wonLostMessage() : String
    {
        var message = ""
        if(isWon()) message = "You Won!"
        else if(isLost()) message = "You Lost!"

        message += "The word was $secretWord."

        return message
    }
}