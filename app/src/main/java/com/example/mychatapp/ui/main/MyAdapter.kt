package com.example.mychatapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mychatapp.databinding.MessageRowBinding
import com.example.mychatapp.model.ChatMessage


class MyAdapter() :
    ListAdapter<ChatMessage, MyAdapter.ListViewHolder>(DiffCallback) {


    //Diffing for List Changes
    object DiffCallback : DiffUtil.ItemCallback<ChatMessage>() {

        //Check if items are same
        override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem == newItem
        }

        //Check if item contents are same
        override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
            return oldItem.name == newItem.name
        }
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ListViewHolder(private val binding: MessageRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatItem: ChatMessage) {
            binding.item = chatItem

            //Let the UI know to execute previous bindings
            binding.executePendingBindings()
        }


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        // create a new view
        //create and pass MessageRowBinding
        val binding = MessageRowBinding.inflate(LayoutInflater.from(parent.context))
        //return ViewHolder
        return ListViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        //get the item at position
        val item = getItem(position)
        //pass it to binding holder bind method
        holder.bind(item)
    }


}
