package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ToDoListDao {

    @Insert
    fun insetToDoData(toDoListDataModel: ToDoListDataModel.ToDoList)

    @Update
    fun updateToDoData(toDoListDataModel: ToDoListDataModel.ToDoList)

    @Query(DataBaseQueries.Queries.DELETE_FROM_ID)
    fun deleteToDoData(id: Int)

    @Query(DataBaseQueries.Queries.FETCH_ALL_TODO)
    fun getAllToDoListData(): LiveData<List<ToDoListDataModel.ToDoList>>
}