package com.tassiecomp.newsapp.data.main

import com.tassiecomp.newsapp.data.CurrencyApi
import com.tassiecomp.newsapp.data.CurrencyResponse
import com.tassiecomp.newsapp.data.util.Resource
import retrofit2.Response
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {

    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccesful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }
}