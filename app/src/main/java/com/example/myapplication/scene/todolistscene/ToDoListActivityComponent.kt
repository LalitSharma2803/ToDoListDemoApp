package com.example.myapplication.scene.todolistscene

import dagger.Component

@Component(modules = [ToDoListModule::class])
interface ToDoListActivityComponent {
    fun inject(toDoListActivity: ToDoListActivity)
}