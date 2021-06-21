package com.example.projectstem.ui.games

import android.util.Log
import com.example.projectstem.R
import com.example.projectstem.model.Word
import kotlin.random.Random

class HangmanGameManager {

    private var lettersUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private lateinit var wordHolder: Word
    private val maxTries = 7
    private var currentTries = 0
    private var drawable: Int = R.drawable.hangman_state_0

    /**
     * Main initialization of the game
     * @param lettersUsed gets the number of letters currently used out of the maximum amount
     * @param currentTries shows the tries remaining until the player loses
     */
    fun startNewGame(wordList : List<Word>): HangmanGameState {
        lettersUsed = ""
        currentTries = 0
        drawable = R.drawable.hangman_state_7

        val randomIndex = Random.nextInt(0, wordList.size)

        wordHolder = wordList[randomIndex]

        wordToGuess = wordHolder.original

        Log.d("epic_gamer", "$wordToGuess")

        generateUnderscores(wordToGuess)
        return getGameState()
    }
    /**
     * @function replaces the letters of the correct word with underscores
     */
    fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == '/') {
                sb.append('/')
            } else {
                sb.append("_")
            }
        }
        underscoreWord = sb.toString()
    }

    /**
     * Checks if the game state is running
     * Checks if the selected letter corresponds to a letter in the word
     * Replaces the underscores with the correct letters when guessed
     * Draws the hangman
     */

    fun play(letter: Char): HangmanGameState {
        if (lettersUsed.contains(letter)) {
            return HangmanGameState.Running(lettersUsed, underscoreWord, drawable)
        }

        lettersUsed += letter
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)) {
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord // _ _ _ _ _ _ _ -> E _ _ _ _ _ _
        indexes.forEach { index ->
            val sb = StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }

        if (indexes.isEmpty()) {
            currentTries++
        }

        underscoreWord = finalUnderscoreWord
        return getGameState()
    }

    /**
     * Gets the images of the hangman
     */

    private fun getHangmanDrawable(): Int {
        return when (currentTries) {
            0 -> R.drawable.hangman_state_0
            1 -> R.drawable.hangman_state_1
            2 -> R.drawable.hangman_state_2
            3 -> R.drawable.hangman_state_3
            4 -> R.drawable.hangman_state_4
            5 -> R.drawable.hangman_state_5
            6 -> R.drawable.hangman_state_6
            7 -> R.drawable.hangman_state_7
            else -> R.drawable.hangman_state_7
        }
    }

    /**
     * Gets the current game state (eg. lost/won/running)
     */

    private fun getGameState(): HangmanGameState {
        if (underscoreWord.equals(wordToGuess, true)) {
            return HangmanGameState.Won(wordToGuess)
        }

        if (currentTries == maxTries) {
            return HangmanGameState.Lost(wordToGuess)
        }

        drawable = getHangmanDrawable()
        return HangmanGameState.Running(lettersUsed, underscoreWord, drawable)
    }
}