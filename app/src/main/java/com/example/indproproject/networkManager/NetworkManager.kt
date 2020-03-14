package com.example.indproproject.networkManager

import com.example.indproproject.networkCalls.ApiClient
import com.example.indproproject.networkCalls.ApiInterface

object NetworkManager {
    private var apiInterface: ApiInterface? = null
    val api: ApiInterface?
        get() {
            if (apiInterface == null) {
                apiInterface = ApiClient.instance
            }
            return apiInterface
        }
}