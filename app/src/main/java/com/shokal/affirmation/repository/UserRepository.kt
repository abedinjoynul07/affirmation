package com.shokal.affirmation.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shokal.affirmation.model.User

class UserRepository {
    private val databaseReference = FirebaseDatabase.getInstance().getReference("users")

    @Volatile
    private var INSTANCE: UserRepository? = null

    fun getInstance(): UserRepository {
        return INSTANCE ?: synchronized(this) {
            val instance = UserRepository()
            INSTANCE = instance
            instance
        }
    }


    fun loadUsers(userList: MutableLiveData<List<User>>) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    var data: List<User> = snapshot.children.map {
                        it.getValue(User::class.java)!!
                    }
                    userList.postValue(data)

                    Log.d("Data", data.toString())
                } catch (e: Exception) {
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}