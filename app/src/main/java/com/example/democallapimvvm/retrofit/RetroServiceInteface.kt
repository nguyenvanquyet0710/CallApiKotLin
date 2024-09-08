package com.example.democallapimvvm.retrofit

import com.example.democallapimvvm.model.QuoteResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInteface {
    @GET("/quotes")
    fun getQuoteList(): Call<QuoteResponse>
}
