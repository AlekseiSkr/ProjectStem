package com.example.projectstem.model

import androidx.room.*

@Entity(tableName = "group_language")
data class Group(
    @PrimaryKey val group_id: Int,
    @ColumnInfo(name = "language1") val language1 : String,
    @ColumnInfo(name = "language2") val language2: String,
)

@Entity(foreignKeys = [ForeignKey(entity = Group::class,
    parentColumns = arrayOf("parentClassColumn"),
    childColumns = arrayOf("childClassColumn"),
    onDelete = ForeignKey.CASCADE)]
)
data class Word(
    val group_language_id : Int,
    @ColumnInfo(name = "original") val original : String,
    @ColumnInfo(name = "translation") val translation : String,
    @ColumnInfo(name = "knowledge") val knowledge : Int,
)

data class GroupAndWord(
    @Embedded val group : Group,
    @Relation(
        parentColumn = "group_id",
        entityColumn = "group_language_id"
    )
    val words : List<Word>
)