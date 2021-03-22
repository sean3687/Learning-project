package com.tassiecomp.retrofitwithpost.api

import com.tassiecomp.retrofitwithpost.model.Post
import retrofit2.http.GET

interface SimpleApi {
    @GET("posts/1")
    suspend fun getPost(): Post

}