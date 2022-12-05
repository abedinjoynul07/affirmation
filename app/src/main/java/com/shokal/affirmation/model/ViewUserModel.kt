package com.shokal.affirmation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shokal.affirmation.repository.UserRepository

class ViewUserModel : ViewModel() {
    private val repository: UserRepository = UserRepository().getInstance()
    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers: LiveData<List<User>> = _allUsers

    init {
        repository.loadUsers(_allUsers)
    }
}