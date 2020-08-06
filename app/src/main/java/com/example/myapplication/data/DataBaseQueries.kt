package com.example.myapplication.data

class DataBaseQueries {
    interface Queries {
        companion object {
            const val DELETE_FROM_ID = "DELETE FROM to_do_list WHERE id = :id"
            const val FETCH_ALL_TODO = "SELECT * FROM to_do_list"
        }
    }
}