package com.example.projectstem.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Group(
    @PrimaryKey val group_id: Int,
    val language1 : String,
    val language2: String,
)
