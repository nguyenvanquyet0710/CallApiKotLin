package com.example.democallapimvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.democallapimvvm.model.QuoteResponse
import com.example.democallapimvvm.retrofit.RetroInstance
import com.example.democallapimvvm.retrofit.RetroServiceInteface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    var liveDataList: MutableLiveData<List<QuoteResponse>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<QuoteResponse>> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInteface::class.java)
        val call = retroService.getQuoteList()
        call.enqueue(object : Callback<QuoteResponse> {
            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful) {
                    Log.d("API Response", "Data: ${response.body()?.results}")
                    liveDataList.postValue(response.body()?.results?.let { listOf(response.body()!!) })
                } else {
                    Log.e("API Response", "Error: ${response.errorBody()?.string()}")
                    liveDataList.postValue(null)
                }
            }
        })
    }
}
