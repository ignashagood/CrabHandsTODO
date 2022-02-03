package com.example.crabhandsdo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.crabhandsdo.databinding.FragmentListBinding

class ListFragment : Fragment() {


    private val viewModel: ViewModelList by viewModels()
    private val adapter = TaskAdapter()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        binding.addButton.setOnClickListener {
            viewModel.addNewTask()
        }
        return binding.root
    }

    fun updateView(state: State) {
        when (state) {
            is Loading -> {}
            is Success -> {
                adapter.updateList(state.taskList, state.diffResult)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var swipeContainer: SwipeRefreshLayout = binding.swipeContainer
        swipeContainer.setOnRefreshListener {
            viewModel.refresh()
            swipeContainer.isRefreshing = false
        }

        viewModel.state.observe(viewLifecycleOwner, object : Observer<State> {
            override fun onChanged(t: State) {
                updateView(t)
            }
        })
    }


}
