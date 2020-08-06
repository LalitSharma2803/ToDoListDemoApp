package com.example.myapplication.scene.todolistscene

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.myapplication.data.ToDoListDao
import com.example.myapplication.data.ToDoListDataBase
import com.example.myapplication.data.ToDoListDataModel

class ToDoListRepository(application: Application) {
    private var toDoListDao: ToDoListDao
    private var toDoList: LiveData<List<ToDoListDataModel.ToDoList>>

    init {
        val toDoListDataBase: ToDoListDataBase = ToDoListDataBase.getDatabase(application)!!
        toDoListDao = toDoListDataBase.toDoListDao()
        toDoList = toDoListDao.getAllToDoListData()
    }

    fun getAllToDoList(): LiveData<List<ToDoListDataModel.ToDoList>> {
        return toDoList
    }

    fun deleteToDoData(toDoListDataModel: ToDoListDataModel.ToDoList) {
        DeleteToDoDataAsyncTask(toDoListDao).execute(toDoListDataModel.id)
    }

    companion object {
        private class DeleteToDoDataAsyncTask(private val toDoListDao: ToDoListDao) :
            AsyncTask<Int, Void, Void>() {
            override fun doInBackground(vararg p0: Int?): Void? {
                toDoListDao.deleteToDoData(p0[0]!!)
                return null
            }
        }
    }
}