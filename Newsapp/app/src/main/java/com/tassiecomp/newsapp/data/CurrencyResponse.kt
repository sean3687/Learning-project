package com.tassiecomp.newsapp.data

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: Rates
)