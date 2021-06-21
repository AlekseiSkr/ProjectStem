package com.example.projectstem.ui.games

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.example.projectstem.R
import com.example.projectstem.model.AppDatabase
import com.example.projectstem.model.Word

class HangmanGameActivity : AppCompatActivity() {

    private val gameManager = HangmanGameManager()
    private lateinit var wordTextView: TextView
    private lateinit var lettersUsedTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var gameLostTextView: TextView
    private lateinit var gameWonTextView: TextView
    private lateinit var newGameButton: Button
    private lateinit var lettersLayout: ConstraintLayout
    private var groupId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hangman)
        imageView = findViewById(R.id.imageView)
        wordTextView = findViewById(R.id.wordTextView)
        lettersUsedTextView = findViewById(R.id.lettersUsedTextView)
        gameLostTextView = findViewById(R.id.gameLostTextView)
        gameWonTextView = findViewById(R.id.gameWonTextView)
        newGameButton = findViewById(R.id.newGameButton)
        lettersLayout = findViewById(R.id.lettersLayout)

        //Initiate Bundle
        val bundle = intent.extras
        //Get groupId From bundle
        groupId = bundle!!.getInt("grpId", 0)
        //Get List of words with selected id
        val wordList = AppDatabase.getDatabase(applicationContext).wordDao().getGameWordsInGroup(groupId)

        //Log.d("DataPass", "datapass : $groupId")
        newGameButton.setOnClickListener {
            startNewGame(wordList)
        }
        val gameState = gameManager.startNewGame(wordList)
        updateUI(gameState)

        lettersLayout.children.forEach { letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    val gameState = gameManager.play((letterView).text[0])
                    updateUI(gameState)
                    letterView.visibility = View.GONE
                }
            }
        }
    }

    /**
     * This private function shows the updating User Interface
     * @property updateUI the name of the function
     * @constructor see if the user won or lost in the hangman game
     * @return the result of the game
     */
    private fun updateUI(hangmanGameState: HangmanGameState) {
        when (hangmanGameState) {
            is HangmanGameState.Lost -> showGameLost(hangmanGameState.wordToGuess)
            is HangmanGameState.Running -> {
                wordTextView.text = hangmanGameState.underscoreWord
                lettersUsedTextView.text = "Letters used: ${hangmanGameState.lettersUsed}"
                imageView.setImageDrawable(ContextCompat.getDrawable(this, hangmanGameState.drawable))
            }
            //On Win
            is HangmanGameState.Won -> {
                showGameWon(hangmanGameState.wordToGuess)
                //Increment Word Knowledge if knowledge is not max
                if (AppDatabase.getDatabase(applicationContext).wordDao().getKnowledgeFromWord(hangmanGameState.wordToGuess, groupId) < 3) {
                    AppDatabase.getDatabase(applicationContext).wordDao()
                        .incrementKnowledgeFromWord(hangmanGameState.wordToGuess, groupId)
                }
            }

        }
    }

    /**
     * This private function shows if the user lost
     * @property showGameLost is the name of this function
     * @constructor get the user's guesses
     * @return show the user's losing result
     */
    private fun showGameLost(wordToGuess: String) {
        wordTextView.text = wordToGuess
        gameLostTextView.visibility = View.VISIBLE
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hangman_state_7))
        lettersLayout.visibility = View.GONE
    }

    /**
     * This private function shows if the user won
     * @property showGameWon is the name of this function
     * @constructor get the user's guesses
     * @return show the user's winning result
     */
    private fun showGameWon(wordToGuess: String) {
        wordTextView.text = wordToGuess
        gameWonTextView.visibility = View.VISIBLE
        lettersLayout.visibility = View.GONE
    }

    /**
     * This private function starts a new game after the user already finished the one
     * @param gameState starts a game using the word list
     * @property startNewGame is the name of this function
     * @constructor The previous game's lost / won page will be gone
     * The letters' layout will be visible again
     * @return updateUI function
     */
    private fun startNewGame(wordList: List<Word>) {
        gameLostTextView.visibility = View.GONE
        gameWonTextView.visibility = View.GONE
        val gameState = gameManager.startNewGame(wordList)
        lettersLayout.visibility = View.VISIBLE
        lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }
}