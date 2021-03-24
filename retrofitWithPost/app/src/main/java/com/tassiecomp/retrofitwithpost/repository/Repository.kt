package com.tassiecomp.retrofitwithpost.repository

import com.tassiecomp.retrofitwithpost.api.RetrofitInstance
import com.tassiecomp.retrofitwithpost.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number:Int): Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }
}