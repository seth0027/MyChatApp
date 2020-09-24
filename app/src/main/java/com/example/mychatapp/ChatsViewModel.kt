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

    private var messageTextLive = MutableLiveData<String>()

    var messageText: String
        get() = messageTextLive.value ?: ""
        set(value) {
            isClickable.value = value.trim().isNotEmpty()
            messageTextLive.value = value
        }


    init {

        isClickable.value = false
        getInProgress()
        loadChatList()
    }


    private fun loadChatList() {

        //Fetch list from DB here

        //MockList

       Handler(Looper.myLooper()!!).postDelayed({

            val localChatList = mutableListOf<ChatMessage>(
                ChatMessage("HEY", "annonymous1"),
                ChatMessage("hello", "unknown"), ChatMessage
                    ("Whats UPP", "UKnow")
            )

            chatList.value = localChatList

            getInProgress()


        }, 2000)

    }

    fun onSend() {

        val chatMessage = ChatMessage(messageTextLive.value!!, "Tester")
        chatList.value!!.add(chatMessage)
        clearMessageText()


    }
    private fun clearMessageText(){
        this.messageTextLive.value=""
        messageText=""
    }


    private fun getInProgress() {
        inProgress.value = chatList.value == null || chatList.value!!.size <= 0
    }


}
