package com.example.indproproject.networkCalls

import com.example.indproproject.models.Facts
import retrofit2.Call
import retrofit2.http.GET

/**
 * this interface contains all get and post and put methods of network calls
 */
interface ApiInterface {
    @get:GET("facts.json")
    val factsList: Call<Facts>
}