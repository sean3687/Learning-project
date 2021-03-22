package com.tassiecomp.retrofitwithpost.repository

import com.tassiecomp.retrofitwithpost.api.RetrofitInstance
import com.tassiecomp.retrofitwithpost.model.Post

class Repository {

    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }
}