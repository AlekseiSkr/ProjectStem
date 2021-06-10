package com.example.projectstem.model

import androidx.room.*

@Entity
data class Group(
    @PrimaryKey val group_id: Int,
    val language1 : String,
    val language2: String,
)

@Entity
data class Word(
    @ColumnInfo val group_language_id : Int,
    @ColumnInfo val original : String,
    @ColumnInfo val translation : String,
    @ColumnInfo val knowledge : Int,
)

data class GroupAndWord(
    @Embedded val group : Group,
    @Relation(
        parentColumn = "group_id",
        entityColumn = "group_language_id"
    )
    val word : Word
)