package com.example.projectstem.ui.games

/**
 * Data class of the Quiz game which is used to define the structure along with the datatype for the Quiz Game
 */

data class QuizQuestion(
    val id: Int,
    val question: String,
    val wordInQuestion: String,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
)