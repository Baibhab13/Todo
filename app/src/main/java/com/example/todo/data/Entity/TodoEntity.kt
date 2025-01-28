package com.example.todo.data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val statuses: List<Boolean>
)
