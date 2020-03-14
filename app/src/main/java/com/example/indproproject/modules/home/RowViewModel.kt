package com.example.indproproject.modules.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.indproproject.models.Facts
import com.example.indproproject.networkCalls.NetworkCallsManager

/**
 * used to call facts from server using network calls
 */
class RowViewModel : ViewModel() {
    fun getFacts(): MutableLiveData<Facts>? {
        return NetworkCallsManager().rows
    }
}