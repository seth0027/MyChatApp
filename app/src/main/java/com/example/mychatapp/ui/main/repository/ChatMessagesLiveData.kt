package com.example.mychatapp.ui.main.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mychatapp.model.ChatMessage
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration

class ChatMessagesLiveData(private val collectionReference: CollectionReference) :
    LiveData<List<ChatMessage>>() {

    private lateinit var listenerRegistration: ListenerRegistration
    override fun onActive() {
        super.onActive()
        listenerRegistration = collectionReference.addSnapshotListener { value, error ->

            if (error != null) {
                Log.w(TAG, "listen:error", error)
                return@addSnapshotListener
            }
            if (!(value!!.isEmpty)) {

                this.postValue(value.toObjects(ChatMessage::class.java))


            }

        }
    }

    override fun onInactive() {
        super.onInactive()
        listenerRegistration.remove()
    }
}
