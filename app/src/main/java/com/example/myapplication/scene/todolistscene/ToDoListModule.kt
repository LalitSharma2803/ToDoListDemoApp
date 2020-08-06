package com.example.myapplication.scene.todolistscene

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ToDoListModule(private val application: Application) {
    @Provides
    fun provideToDoListViewModel(): ToDoListViewModel {
        return ToDoListViewModel(application)
    }
}