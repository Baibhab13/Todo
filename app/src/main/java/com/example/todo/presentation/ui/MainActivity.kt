package com.example.todo.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.todo.data.database.TodoDatabase
import com.example.todo.presentation.theme.TodoTheme
import com.example.todo.presentation.viewmodel.TodoViewModel

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {

    // Created a instance of the viewModel and passed the database db.dao
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            name = "todos.database"
        ).build()
    }
    private val viewModel by viewModels<TodoViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoViewModel(db.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AddTodoBottomBar(viewModel = viewModel)
                    },
                    content = { innerPadding ->
                        TodoScreen(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
                    },
                )
            }
        }
    }
}

