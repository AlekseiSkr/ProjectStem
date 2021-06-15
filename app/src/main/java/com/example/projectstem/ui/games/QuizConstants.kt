package com.example.projectstem.ui.games

import android.util.Log
import com.example.projectstem.model.Word
import kotlin.random.Random

object QuizConstants {

    // TODO (STEP 1: Create a constant variables which we required in the result screen.)
    // START
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"



    // END
    
    fun getQuestions(wordList: List<Word>): ArrayList<QuizQuestion> {

        val questionsList = ArrayList<QuizQuestion>()

        for(i in 1..wordList.size){
            try {
                var listOFWords = wordList
                var id: Int = 1
                var randomIndex = Random.nextInt(0, listOFWords.size)
                val word = listOFWords[randomIndex]
                var wordInQuestion: String = word.original
                var correctAnwser: String = word.translation
                var option1: String = if (listOFWords[randomIndex].translation != correctAnwser) {
                    listOFWords[randomIndex].translation
                } else {
                    listOFWords!![randomIndex + 1].translation
                }
                var option2: String = listOFWords[randomIndex].translation
                var option3: String = listOFWords[randomIndex].translation

                val newQuestion = QuizQuestion(id, "What does this word mean?", wordInQuestion, correctAnwser, option1, option2, option3, 1)
                questionsList.add(newQuestion)
                id++
                Log.d("quizGaming", "$wordInQuestion, $correctAnwser, $option1, $option2, $option3")

            }catch (e: Exception){
                Log.d("quizGaming", "$e")
            }

                //INITIALIZE VARS
                if (i == 6) break
            }

        return questionsList
    }
}