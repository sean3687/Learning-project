package com.tassiecomp.retrofitwithpost.api

import com.tassiecomp.retrofitwithpost.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleApi {
    @GET("posts/1")
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
    @GET("posts/{postNumber}")
    suspend fun getPost2(
        @Path("PostNumber") number:Int
    ): Response<Post>

}