package com.example.crabhandsdo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.crabhandsdo.databinding.FragmentMainBinding


class MainFragment : Fragment() {

   lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view_child, ListFragment())
                .commit()
        }
        binding.button2.setOnClickListener {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view_child, TodayFragment())
                .commit()
        }
        return binding.root
    }

}