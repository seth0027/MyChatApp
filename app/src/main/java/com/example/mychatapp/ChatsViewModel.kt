package com.example.mychatapp

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychatapp.model.ChatMessage

class ChatsViewModel : ViewModel() {


    val chatList = MutableLiveData<MutableList<ChatMessage>>()


    val inProgress = MutableLiveData<Boolean>()


    val isClickable = MutableLiveData<Boolean>()

    val messageTextLive = MutableLiveData<String>()


    init {

        isClickable.value = false
        getInProgress()
        loadChatList()

        //Keep track of the edit txt content to make button clickable or no
        messageTextLive.observeForever {
            isClickable.value = it.trim().isNotEmpty()
        }
    }


    private fun loadChatList() {

        //Fetch list from DB here

        //MockList

        Handler(Looper.myLooper()!!).postDelayed({

            val localChatList = mutableListOf<ChatMessage>(
                ChatMessage("HEY", "annonymous1")
            )



            chatList.value = localChatList

            getInProgress()


        }, 2000)

    }

    fun onSend() {

        val chatMessage = ChatMessage(messageTextLive.value!!, "Tester")

        val newList = mutableListOf<ChatMessage>()
        chatList.value?.let { newList.addAll(it) }
        newList.add(chatMessage)

        chatList.value = newList
        clearMessageText()


    }

    private fun clearMessageText() {
        this.messageTextLive.value = ""

    }


    private fun getInProgress() {
        inProgress.value = chatList.value == null || chatList.value!!.size <= 0
    }


}
