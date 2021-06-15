package com.example.projectstem.ui.games

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