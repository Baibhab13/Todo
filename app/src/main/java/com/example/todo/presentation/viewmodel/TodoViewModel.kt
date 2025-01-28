package com.example.todo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.Entity.TodoEntity
import com.example.todo.data.dao.TodoDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel (private val todoDao: TodoDao): ViewModel(){
    /*
    *
    */
    private val _todos = MutableStateFlow <List<TodoEntity>>(emptyList())
    val todos: StateFlow<List<TodoEntity>> = _todos
    /*
    This is the oncreate of the application and it will be show as per the app started
    */
    init {
        viewModelScope.launch {
            todoDao.getAllTodosById().collect{ todos ->
                _todos.value = todos
            }
        }
    }
    /*
    * this function is for to insert and update (upsert) the todo task
    */
    fun insertTodo(todoTitle: String) {
        val todo = TodoEntity(title = todoTitle, statuses = List(3){false})
        viewModelScope.launch {
            todoDao.upsertTodo(todo)
        }
    }
    /*
    * this function is for to delete the todo task
    */
    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            todoDao.deleteTodo(todo)
        }
    }

    fun updateTodo(todoEntity: TodoEntity, newStatuses: List<Boolean>) {
        viewModelScope.launch {
            todoDao.upsertTodo(todoEntity = todoEntity.copy(statuses = newStatuses))
        }
    }
}