package com.tassiecomp.retrofitwithpost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tassiecomp.retrofitwithpost.model.Post
import com.tassiecomp.retrofitwithpost.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myResponse: MutableLiveData<Post> =

    fun getPost() {
        viewModelScope.launch {

        }
    }
}
