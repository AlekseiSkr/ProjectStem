package com.example.projectstem.ui.games

import com.example.projectstem.R
import com.example.projectstem.model.Word
import kotlin.random.Random

object QuizConstants {

    // TODO (STEP 1: Create a constant variables which we required in the result screen.)
    // START
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    var listOFWords: List<Word> = emptyList()
    var id: Int = 0
    var word: Word = Word(0, 1, "dab", "haters", 1)
    val randomIndex = Random.nextInt(0, word.word_id)
    var wordInQuestion: String = word.original
    var correctAnwser: String = word.translation
    var option1: String = if(listOFWords[randomIndex].translation != correctAnwser){ listOFWords[randomIndex].translation }else{ listOFWords!![randomIndex + 1].translation}
    var option2: String = listOFWords[randomIndex].translation
    var option3: String = listOFWords[randomIndex].translation


    // END
    
    fun getQuestions(): ArrayList<QuizQuestion> {
        val questionsList = ArrayList<QuizQuestion>()

        // 1

        val que1 = QuizQuestion(
            1, "What kind of animal is this?",
            R.drawable.ic_animal_opossum,
            "Racoon", "Possum",
            "Opossum", "Aardvark", 3
        )

        questionsList.add(que1)

        // 2
        val que2 = QuizQuestion(
            2, "What hand-tool is this?",
            R.drawable.ic_tool_chisel,
            "Wrench", "Chisel",
            "Level", "Screwdriver", 2
        )

        questionsList.add(que2)

        // 3
        val que3 = QuizQuestion(
            3, "What music instrument is displayed in the image?",
            R.drawable.ic_music_trombone,
            "Trumpet", "Clarinet",
            "French horn", "Trombone", 4
        )

        questionsList.add(que3)

        // 4
        val que4 = QuizQuestion(
            4, "What type of vehicle is this?",
            R.drawable.ic_vehicle_front_loader,
            "Front loader", "Bulldozer",
            "Backhoe Loader", "Excavator", 1
        )

        questionsList.add(que4)

        // 5
        val que5 = QuizQuestion(
            5, "What is displayed in this picture?",
            R.drawable.ic_animal_harambe,
            "Child", "Gorilla",
            "Harambe", "Chimpanzee", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = QuizQuestion(
            6, "What kind of animal is this?",
            R.drawable.ic_animal_armadhillo,
            "Armadillo", "African Ratchet",
            "Soak back opossum", "none of these", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = QuizQuestion(
            7, "What material is displayed in the picture",
            R.drawable.ic_material_sandstone,
            "Gravel", "Obsidian",
            "Sandstone", "Limestone", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = QuizQuestion(
            8, "How is this place called?",
            R.drawable.ic_place_bazzar,
            "Shop", "Supermarket",
            "Market", "Bazaar", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = QuizQuestion(
            9, "What vegetable is displayed in the picture?",
            R.drawable.ic_vegetable_artichoke,
            "Celery", "Artichoke",
            "Beets", "Cabbage", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = QuizQuestion(
            10, "What this person is holding?",
            R.drawable.ic_tool_scythe,
            "Saw", "Scythe",
            "Blade", "None of the above", 2
        )

        questionsList.add(que10)

        return questionsList
    }
}