package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Database Singleton
@Database(entities = [ToDoListDataModel.ToDoList::class], version = 1)
abstract class ToDoListDataBase : RoomDatabase() {

    abstract fun toDoListDao(): ToDoListDao

    companion object {

        @Volatile
        private var INSTANCE: ToDoListDataBase? = null

        fun getDatabase(context: Context): ToDoListDataBase? {
            if (INSTANCE == null) {
                synchronized(ToDoListDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            ToDoListDataBase::class.java, "to_do_list_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }

}