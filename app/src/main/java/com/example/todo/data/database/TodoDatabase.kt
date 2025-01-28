package com.example.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.data.Entity.TodoEntity
import com.example.todo.data.converters.TodoConverters
import com.example.todo.data.dao.TodoDao

@Database(
    entities = [TodoEntity::class],
    version = 1
)
@TypeConverters(TodoConverters::class)
abstract class TodoDatabase : RoomDatabase(){
    abstract val dao: TodoDao
}