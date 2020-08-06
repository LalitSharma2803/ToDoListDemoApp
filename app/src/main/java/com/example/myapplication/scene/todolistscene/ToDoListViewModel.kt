package com.example.myapplication.scene.todolistscene

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.data.ToDoListDataModel

class ToDoListViewModel(@NonNull application: Application) : AndroidViewModel(application) {
    private val repository: ToDoListRepository = ToDoListRepository(application)

    fun getToDoListData(): LiveData<List<ToDoListDataModel.ToDoList>> {
        return repository.getAllToDoList()
    }

    fun deleteToDoData(toDoListDataModel: ToDoListDataModel.ToDoList) {
        repository.deleteToDoData(toDoListDataModel)
    }
}