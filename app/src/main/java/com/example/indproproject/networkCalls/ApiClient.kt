package com.example.indproproject.networkCalls

import com.example.indproproject.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * this class is used to build retrofit for api calls
 */
object ApiClient {
    var retrofit: Retrofit? = null
    @get:Synchronized
    val instance: ApiInterface
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create(ApiInterface::class.java)
        }
}