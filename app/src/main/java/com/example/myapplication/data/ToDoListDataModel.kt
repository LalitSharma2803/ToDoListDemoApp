package com.example.myapplication.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

//Apps table model are placed here
class ToDoListDataModel {

//    To Do List table
    @Entity(tableName = "to_do_list")
    data class ToDoList(

        @PrimaryKey(autoGenerate = true)
        @NonNull
        @ColumnInfo(name = "id")
        var id: Int,

        @NonNull
        @ColumnInfo(name = "task_type")
        var taskType: String,

        @NonNull
        @ColumnInfo(name = "task_priority")
        var taskPriority: String,

        @NonNull
        @ColumnInfo(name = "task_description")
        var taskDescription: String
    ): Serializable

}