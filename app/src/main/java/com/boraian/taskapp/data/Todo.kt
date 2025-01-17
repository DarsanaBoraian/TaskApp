package com.boraian.taskapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val task: String,
    val isFavourite: Boolean
)
