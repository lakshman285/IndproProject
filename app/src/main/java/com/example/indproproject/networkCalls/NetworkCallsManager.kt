package com.example.indproproject.networkCalls

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.indproproject.Constants
import com.example.indproproject.models.Facts
import com.example.indproproject.networkManager.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * this class is contains success and failure methods while gettig response from server
 */
class NetworkCallsManager private constructor() {

    val instance = NetworkCallsManager()
    var mutableLiveData: MutableLiveData<Facts>? = null
    var apiManager: ApiInterface = NetworkManager.api!!

    val rows: MutableLiveData<Facts>
        get() {
            mutableLiveData = MutableLiveData()
            apiManager.factsList.enqueue(object : Callback<Facts> {
                override fun onResponse(
                    call: Call<Facts>,
                    response: Response<Facts>
                ) {
                    if (response.body() != null && response.isSuccessful) {
                        mutableLiveData!!.value = response.body()
                    }
                }

                override fun onFailure(
                    call: Call<Facts>,
                    t: Throwable
                ) {
                    Log.e(Constants.TAG_NETWORK_CALLS_MANAGER, "${t.message}")
                }
            })
            return mutableLiveData!!
        }
}