package com.example.myapplication.data

//All DB queries are grouped in the class
class DataBaseQueries {

    //All TO DO table queries are grouped into this interface
    interface ToDoTableQueries {
        companion object {
            const val DELETE_FROM_ID = "DELETE FROM to_do_list WHERE id = :id"
            const val FETCH_ALL_TODO = "SELECT * FROM to_do_list"
        }
    }
}