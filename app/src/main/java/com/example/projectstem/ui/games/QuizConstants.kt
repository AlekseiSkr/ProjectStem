package com.example.projectstem.ui.games

import android.util.Log
import com.example.projectstem.model.Word
import kotlin.random.Random

object QuizConstants {

    // Create a constant variables which we required in the result screen
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun randomNumber(list: List<Word>) : Int{
        return Random.nextInt(0, list.size)
    }

    /**
     *  initialization of the quiz game questions along with the answer options
     * @param wordList is the list of words that is used to store word values for the Quiz
     * @return returns questions compiled from the QuizQuestion Class
     */
    
    fun getQuestions(wordList: List<Word>): ArrayList<QuizQuestion> {

        val questionsList = ArrayList<QuizQuestion>()

        for(i in 1..wordList.size){
            try {
                var listOFWords = wordList
                var id: Int = 1
                val word = listOFWords[randomNumber(wordList)]
                var wordInQuestion: String = word.original
                var correctAnwser: String = word.translation
                var option1: String = listOFWords[randomNumber(wordList)].translation
                var option2: String = listOFWords[randomNumber(wordList)].translation
                var option3: String = listOFWords[randomNumber(wordList)].translation

                val newQuestion = QuizQuestion(id, "What does this word mean?", wordInQuestion, correctAnwser, option1, option2, option3, 1)
                questionsList.add(newQuestion)
                id++
                Log.d("quizGaming", "$wordInQuestion, $correctAnwser, $option1, $option2, $option3")

            }catch (e: Exception){
                Log.d("quizGaming", "$e")
            }

                //INITIALIZE VARS
                if (i == 10) break
            }

        return questionsList
    }
}