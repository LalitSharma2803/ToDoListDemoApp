package com.example.myapplication.scene.addoreditscene

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.data.ToDoListDataModel

class AddOrEditToDoViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private val repository: AddOrEditRepository = AddOrEditRepository(application)

    fun insertToDoData(taskType: String, taskPriority: String, taskDescription: String) {
        repository.addToDoData(
            ToDoListDataModel.ToDoList(
                0,
                taskType,
                taskPriority,
                taskDescription
            )
        )
    }

    fun updateToDoData(id: Int, taskType: String, taskPriority: String, taskDescription: String) {
        repository.updateToDoData(
            ToDoListDataModel.ToDoList(
                id,
                taskType,
                taskPriority,
                taskDescription
            )
        )
    }
}