package com.example.crabhandsdo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil

class ViewModelList : ViewModel() {

    var state: MutableLiveData<State> = MutableLiveData()

    init {
        updateList()
    }

    private fun updateList() {
        val newTaskList = listOf(
            "Сделать приложение",
            "Пропылесосить",
            "Насрать в штаны",
            "Вытереть пыль"
        ).map { Task(it) }
        updateList(newTaskList)
    }

    fun addNewTask() {
        state.value.let {
            if (it is Success) {
                val newTaskList = it.taskList + Task("Посрать")
                updateList(newTaskList)
            }
        }
    }

    private fun updateList(newTaskList: List<Task>) {
        val currentTaskList: List<Task> = (state.value as? Success)?.taskList ?: emptyList()
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int {
                return currentTaskList.size
            }

            override fun getNewListSize(): Int {
                return newTaskList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldTask: Task = currentTaskList[oldItemPosition]
                val newTask: Task = newTaskList[newItemPosition]
                return oldTask.name == newTask.name
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldTask: Task = currentTaskList[oldItemPosition]
                val newTask: Task = newTaskList[newItemPosition]
                return oldTask == newTask
            }

        })
        state.value = Success(newTaskList, diffResult)
    }

    fun refresh() {
        state.value.let {
            if (it is Success) {
                updateList(it.taskList.shuffled())
            }
        }
    }
}