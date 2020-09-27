package com.example.mychatapp.ui.main.repository

import com.example.mychatapp.model.ChatMessage

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


interface ChatMessageRepository {
    fun addChatMessage(chatMessage: ChatMessage): Boolean
    fun getChatMessages(): ChatMessagesLiveData
}

class FireStoreChatMessageRepository : ChatMessageRepository {

    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    companion object {
        const val MESSAGES = "messages"
    }

    override fun addChatMessage(chatMessage: ChatMessage): Boolean {

        var success = false
        db.collection(MESSAGES).add(chatMessage).addOnSuccessListener {
            success = true

        }.addOnFailureListener {
            success = false
        }

        return success


    }

    override fun getChatMessages(): ChatMessagesLiveData {
        val ref = db.collection(MESSAGES)

        return ChatMessagesLiveData(ref)
    }


}
