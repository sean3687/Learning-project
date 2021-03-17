package com.tassiecomp.newsapp.data.main

import com.tassiecomp.newsapp.data.CurrencyResponse
import com.tassiecomp.newsapp.data.util.Resource

interface MainRepository {

    suspend fun getRates (base:String): Resource<CurrencyResponse>
}