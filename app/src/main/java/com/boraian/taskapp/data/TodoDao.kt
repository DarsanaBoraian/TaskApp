package com.boraian.taskapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo:Todo)

    @Query("SELECT * FROM Todo where id = :id")
    suspend fun getTodoById(id:Int): Todo

    @Query("SELECT * FROM Todo")
    fun getAllTodos(): Flow<List<Todo>>
}