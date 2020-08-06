package com.example.myapplication.scene.todolistscene

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.common.Constants
import com.example.myapplication.data.ToDoListDataModel
import com.example.myapplication.scene.addoreditscene.AddOrEditToDoActivity
import kotlinx.android.synthetic.main.activity_to_do_list.*
import javax.inject.Inject


class ToDoListActivity : AppCompatActivity(), ToDoListAdapter.ToDoTaskClickListener {
    private lateinit var toDoListAdapter: ToDoListAdapter
    private var toDoList = mutableListOf<ToDoListDataModel.ToDoList>()

    @set:Inject
    lateinit var toDoListViewModel: ToDoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        setUpActivity()
        setupToDoListAdapter()
        setupObservers()
        setupViewClicks()
        setupSwipeToDelete()
    }

    private fun setUpActivity() {
        val component =
            DaggerToDoListActivityComponent.builder().toDoListModule(ToDoListModule(application))
                .build()
        component.inject(this)
    }

    private fun setupToDoListAdapter() {
        rvTodoList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        toDoListAdapter = ToDoListAdapter(toDoList, this)
        rvTodoList.adapter = toDoListAdapter
    }

    private fun setupObservers() {
        toDoListViewModel.getToDoListData()
            .observe(this, Observer<List<ToDoListDataModel.ToDoList>>() {
                if (it.isNotEmpty()) {
                    toDoList.clear()
                    toDoList.addAll(it)
                    toDoListAdapter.notifyDataSetChanged()
                    rvTodoList.visibility = View.VISIBLE
                    tvNoToDoList.visibility = View.GONE
                } else {
                    tvNoToDoList.visibility = View.VISIBLE
                    rvTodoList.visibility = View.GONE
                }
            })
    }

    private fun setupViewClicks() {
        fab.setOnClickListener {
            addNewToDo()
        }
    }

    private fun setupSwipeToDelete() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                onItemSwiped(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(rvTodoList)
    }

    private fun onItemSwiped(position: Int) {
        toDoListViewModel.deleteToDoData(toDoListAdapter.toTaskList[position])
    }

    override fun onItemClicked(toDoTask: ToDoListDataModel.ToDoList) {
        updateToDoList(toDoTask)
    }

    private fun addNewToDo() {
        val intent = Intent(this, AddOrEditToDoActivity::class.java)
        intent.putExtra(Constants.IntentExtras.UPDATE_INTENT, false)
        startActivity(intent)
    }

    private fun updateToDoList(toDoTask: ToDoListDataModel.ToDoList) {
        val intent = Intent(this, AddOrEditToDoActivity::class.java)
        intent.putExtra(Constants.IntentExtras.UPDATE_INTENT, true)
        intent.putExtra(Constants.IntentExtras.TO_DO_Task, toDoTask)
        startActivity(intent)
    }
}