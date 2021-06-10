package com.example.projectstem.model

import androidx.room.*

@Entity(tableName = "group_language")
data class Group(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="group_id") var group_id: Int,
    @ColumnInfo(name = "language1") var language1 : String,
    @ColumnInfo(name = "language2") var language2: String,
)

@Entity(foreignKeys = [ForeignKey(entity = Group::class,
    parentColumns = arrayOf("group_id"),
    childColumns = arrayOf("group_language_id"),
    onDelete = ForeignKey.CASCADE)],
    tableName = "words"
)
data class Word(
    @PrimaryKey @ColumnInfo(name = "group_language_id") var group_language_id : Int,
    @ColumnInfo(name = "original") var original : String,
    @ColumnInfo(name = "translation") var translation : String,
    @ColumnInfo(name = "knowledge") var knowledge : Int,
)
