package com.example.myapplication.scene.todolistscene

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.myapplication.R
import com.example.myapplication.data.ToDoListDataModel

class ToDoListAdapter(
    val toTaskList: List<ToDoListDataModel.ToDoList>,
    private val itemClickListener: ToDoTaskClickListener
) : androidx.recyclerview.widget.RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_to_do_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val toDoTask = toTaskList[position]
        holder.tvTaskType.text = toDoTask.taskType
        holder.tvTaskPriority.text = toDoTask.taskPriority
        holder.tvTaskDescription.text = toDoTask.taskDescription

        holder.container.setOnClickListener {
            itemClickListener.onItemClicked(toDoTask)
        }
    }

    override fun getItemCount() = toTaskList.size

    inner class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val tvTaskType: TextView = itemView.findViewById(R.id.tvTaskType)
        val tvTaskPriority: TextView = itemView.findViewById(R.id.tvTaskPriority)
        val tvTaskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
        val container: CardView = itemView.findViewById(R.id.container)
    }

    interface ToDoTaskClickListener {
        fun onItemClicked(toDoTask: ToDoListDataModel.ToDoList)
    }
}
