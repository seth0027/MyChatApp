package com.example.mychatapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mychatapp.ChatsViewModel
import com.example.mychatapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel: ChatsViewModel by viewModels()
        //Inflate the layout
        binding = FragmentMainBinding.inflate(layoutInflater)
        //setting viewModel
        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        //Set the adapter to MyAdapter and submit the List.
        binding.recyclerView.adapter = MyAdapter()

        viewModel.chatList.observe(viewLifecycleOwner, {
            val adapter = binding.recyclerView.adapter as MyAdapter


            adapter.submitList(it)

        })




        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        return binding.root
    }


}

