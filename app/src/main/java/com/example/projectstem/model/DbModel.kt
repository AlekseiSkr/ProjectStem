package com.example.projectstem.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class responsible for modelling and creating a table for the Room Database
 */
@Entity(tableName = "group_language")
data class Group(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="group_id") val group_id: Int,
    @ColumnInfo(name = "language1") val language1 : String,
    @ColumnInfo(name = "language2") val language2: String,
)

/**
 * Data class responsible for modelling and creating a table for the Room Database
 */
@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "word_id") val word_id : Int,
    @ColumnInfo(name = "group_language_id") val group_language_id : Int,
    @ColumnInfo(name = "original") val original : String,
    @ColumnInfo(name = "translation") val translation : String,
    @ColumnInfo(name = "knowledge") val knowledge : Int,
)
