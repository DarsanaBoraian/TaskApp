package com.boraian.taskapp.domain

import com.boraian.taskapp.data.Todo
import com.boraian.taskapp.data.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val dao: TodoDao
) {
    suspend fun insertTodo(todo: Todo)= dao.insertTodo(todo = todo)
    suspend fun updateTodo(todo: Todo)= dao.updateTodo(todo = todo)

    suspend fun deleteTodo(todo: Todo)= dao.deleteTodo(todo = todo)
    suspend fun getTodoById(id: Int)= dao.getTodoById(id = id)

    fun getAllTodos(): Flow<List<Todo>> = dao.getAllTodos()

}