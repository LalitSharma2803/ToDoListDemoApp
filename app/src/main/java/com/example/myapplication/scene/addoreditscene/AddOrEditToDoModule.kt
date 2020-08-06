package com.example.myapplication.scene.addoreditscene

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AddOrEditToDoModule(private val application: Application) {
    @Provides
    fun provideToDoListViewModel(): AddOrEditToDoViewModel {
        return AddOrEditToDoViewModel(application)
    }
}