package com.example.myapplication.scene.addoreditscene

import android.app.Application
import android.os.AsyncTask
import com.example.myapplication.data.ToDoListDao
import com.example.myapplication.data.ToDoListDataBase
import com.example.myapplication.data.ToDoListDataModel

class AddOrEditRepository(application: Application) {

    private var toDoListDao: ToDoListDao

    init {
        val toDoListDataBase: ToDoListDataBase = ToDoListDataBase.getDatabase(application)!!
        toDoListDao = toDoListDataBase.toDoListDao()
    }

    fun addToDoData(toDoListDataModel: ToDoListDataModel.ToDoList) {
        AddToDoDataAsyncTask(toDoListDao).execute(toDoListDataModel)
    }

    fun updateToDoData(toDoListDataModel: ToDoListDataModel.ToDoList) {
        UpdateToDoDataAsyncTask(toDoListDao).execute(toDoListDataModel)
    }

    companion object {
        private class AddToDoDataAsyncTask(private val toDoListDao: ToDoListDao) :
            AsyncTask<ToDoListDataModel.ToDoList, Void, Void>() {
            override fun doInBackground(vararg p0: ToDoListDataModel.ToDoList?): Void? {
                toDoListDao.insetToDoData(p0[0]!!)
                return null
            }
        }

        private class UpdateToDoDataAsyncTask(private val toDoListDao: ToDoListDao) :
            AsyncTask<ToDoListDataModel.ToDoList, Void, Void>() {
            override fun doInBackground(vararg p0: ToDoListDataModel.ToDoList?): Void? {
                toDoListDao.updateToDoData(p0[0]!!)
                return null
            }
        }
    }
}