package com.example.myapplication.scene.addoreditscene

import dagger.Component

@Component(modules = [AddOrEditToDoModule::class])
interface AddOrEditToDoComponent {
    fun inject(addOrEditToDoActivity: AddOrEditToDoActivity)
}