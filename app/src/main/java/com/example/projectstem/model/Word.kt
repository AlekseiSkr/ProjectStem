package com.example.projectstem.model

import androidx.room.*

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Group::class,
    parentColumns = arrayOf("group_id"),
    childColumns = arrayOf("group_id"),
    onDelete = ForeignKey.CASCADE))
)
data class Word(
    @ForeignKey val group_id : Int,
    @ColumnInfo val original : String,
    @ColumnInfo val translation : String,
    @ColumnInfo val knowledge : Int,
)


