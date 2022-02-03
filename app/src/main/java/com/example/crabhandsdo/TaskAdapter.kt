package com.example.crabhandsdo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crabhandsdo.databinding.TaskItemBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>() {

    private val taskList = ArrayList<Task>()

    class TaskHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = TaskItemBinding.bind(item)
        fun bind(task: Task) = with(binding) {
            taskName.text = task.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(taskList[position])
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateList(tasks : List<Task>, diffResult: DiffUtil.DiffResult) {
        taskList.clear()
        taskList.addAll(tasks)
        diffResult.dispatchUpdatesTo(this)
    }
}

sealed class State
class Success(val taskList: List<Task>, val diffResult: DiffUtil.DiffResult) : State()
class Loading : State()
