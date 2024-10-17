package com.boraian.taskapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.boraian.taskapp.data.Todo
import com.boraian.taskapp.domain.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel(){
    var todo by mutableStateOf(Todo(0,"", false))
    private set
    
    val getAllTodos: Flow<List<Todo>> = repository.getAllTodos()
    
    private var deletedTodo: Todo ?= null
    
    fun insertTodo(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTodo(todo = todo)
        }
    }

    fun updateTodo(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodo(todo = todo)
        }
    }

    fun deleteTodo(todo:Todo){
        viewModelScope.launch(Dispatchers.IO) {
            deletedTodo = todo
            repository.deleteTodo(todo = todo)
        }
    }

    fun getTodoById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            todo = repository.getTodoById(id = id)
        }
    }

    fun updateTask(newValue: String){
      todo = todo.copy(task = newValue)
    }

    fun updateFavourite(newValue: Boolean){
        todo = todo.copy(isFavourite = newValue)
    }



}