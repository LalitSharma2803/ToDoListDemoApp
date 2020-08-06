package com.example.myapplication.scene.addoreditscene

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.common.Constants
import com.example.myapplication.data.ToDoListDataModel
import kotlinx.android.synthetic.main.activity_add_or_edit_to_do.*
import javax.inject.Inject

class AddOrEditToDoActivity : AppCompatActivity() {

    private var isTypeUpdate: Boolean = false
    private lateinit var updateToDoItem: ToDoListDataModel.ToDoList

    @set:Inject
    lateinit var addOrEditToDoViewModel: AddOrEditToDoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit_to_do)

        setUpActivity()
        setupSpinners()
        setupViewClicks()

        if (intent.getBooleanExtra(Constants.IntentExtras.UPDATE_INTENT, false)) {
            isTypeUpdate = true
            updateToDoItem =
                intent.getSerializableExtra(Constants.IntentExtras.TO_DO_Task) as ToDoListDataModel.ToDoList
            updateToDoData()
        }
    }

    private fun updateToDoData() {
        etToDoDetails.setText(updateToDoItem.taskDescription)
        spPriority.setSelection(
            (spPriority.adapter as ArrayAdapter<String?>).getPosition(
                updateToDoItem.taskPriority
            )
        )

        spTaskType.setSelection(
            (spTaskType.adapter as ArrayAdapter<String?>).getPosition(
                updateToDoItem.taskType
            )
        )
    }

    private fun setUpActivity() {
        val component =
            DaggerAddOrEditToDoComponent.builder()
                .addOrEditToDoModule(AddOrEditToDoModule(application)).build()
        component.inject(this)
    }

    private fun setupSpinners() {
        ArrayAdapter.createFromResource(
            this,
            R.array.task_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spTaskType.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.priority,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spPriority.adapter = adapter
        }
    }

    private fun setupViewClicks() {
        btSave.setOnClickListener {
            if (etToDoDetails.text.toString().isNotEmpty()) {
                if (isTypeUpdate) {
                    addOrEditToDoViewModel.updateToDoData(
                        updateToDoItem.id,
                        spTaskType.selectedItem.toString(),
                        spPriority.selectedItem.toString(),
                        etToDoDetails.text.toString()
                    )
                    onDataSaved(R.string.old_data_updated)
                } else {
                    addOrEditToDoViewModel.insertToDoData(
                        spTaskType.selectedItem.toString(),
                        spPriority.selectedItem.toString(),
                        etToDoDetails.text.toString()
                    )
                    onDataSaved(R.string.new_data_saved)
                }
            } else {
                Toast.makeText(this, getString(R.string.description_error), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun onDataSaved(messageId: Int) {
        Toast.makeText(this, getString(messageId), Toast.LENGTH_LONG).show()
        this.finish()
    }
}