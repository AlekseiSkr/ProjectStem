package com.example.projectstem.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "group_language")
data class Group(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="group_id") val group_id: Int,
    @ColumnInfo(name = "language1") val language1 : String,
    @ColumnInfo(name = "language2") val language2: String,
)

@Entity(foreignKeys = [ForeignKey(entity = Group::class,
    parentColumns = arrayOf("group_id"),
    childColumns = arrayOf("group_language_id"),
    onDelete = ForeignKey.CASCADE)],
    tableName = "words"
)
data class Word(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "group_language_id") val group_language_id : Int,
    @ColumnInfo(name = "original") val original : String,
    @ColumnInfo(name = "translation") val translation : String,
    @ColumnInfo(name = "knowledge") val knowledge : Int,
)
