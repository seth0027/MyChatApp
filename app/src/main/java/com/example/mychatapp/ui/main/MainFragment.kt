package com.example.mychatapp.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mychatapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout
        binding = FragmentMainBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataList = listOf<String>("SS", "AA", "SS")
        //Set the adapter to MyAdapter and submit the List.
        binding.recyclerView.adapter = MyAdapter(dataList)


        val adapter = binding.recyclerView.adapter as MyAdapter
        adapter.submitList(dataList)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        //Watch for text changes
        binding.chatField.addTextChangedListener {
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    //before nothing to do
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    //enable button if text lenght is >0
                    binding.sendButton.isEnabled = p0.toString().trim().isNotEmpty()

                }

                override fun afterTextChanged(p0: Editable?) {
                    // nothing to do after
                }
            }
        }


    }
}

