package com.example.projectstem.ui.games

import com.example.projectstem.R

object Constants {

    // TODO (STEP 1: Create a constant variables which we required in the result screen.)
    // START
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    // END
    
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        // 1
        val que1 = Question(
            1, "What kind of animal is this?",
            R.drawable.ic_animal_opossum,
            "Racoon", "Possum",
            "Opossum", "Aardvark", 3
        )

        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "What hand-tool is this?",
            R.drawable.ic_tool_chisel,
            "Wrench", "Chisel",
            "Level", "Screwdriver", 2
        )

        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "What music instrument is displayed in the image?",
            R.drawable.ic_music_trombone,
            "Trumpet", "Clarinet",
            "French horn", "Trombone", 4
        )

        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "What type of vehicle is this?",
            R.drawable.ic_vehicle_front_loader,
            "Front loader", "Bulldozer",
            "Backhoe Loader", "Excavator", 1
        )

        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "What is displayed in this picture?",
            R.drawable.ic_animal_harambe,
            "Child", "Gorilla",
            "Harambe", "Chimpanzee", 3
        )

        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "What kind of animal is this?",
            R.drawable.ic_animal_armadhillo,
            "Armadillo", "African Ratchet",
            "Soak back opossum", "none of these", 1
        )

        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "What material is displayed in the picture",
            R.drawable.ic_material_sandstone,
            "Gravel", "Obsidian",
            "Sandstone", "Limestone", 3
        )

        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "How is this place called?",
            R.drawable.ic_place_bazzar,
            "Shop", "Supermarket",
            "Market", "Bazaar", 4
        )

        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "What vegetable is displayed in the picture?",
            R.drawable.ic_vegetable_artichoke,
            "Celery", "Artichoke",
            "Beets", "Cabbage", 2
        )

        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, "What this person is holding?",
            R.drawable.ic_tool_scythe,
            "Saw", "Scythe",
            "Blade", "None of the above", 2
        )

        questionsList.add(que10)

        return questionsList
    }
}