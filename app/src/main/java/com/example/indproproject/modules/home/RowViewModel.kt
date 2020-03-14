package com.example.indproproject.modules.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.indproproject.localDatabase.RowRepository
import com.example.indproproject.models.Facts
import com.example.indproproject.models.Row
import com.example.indproproject.networkCalls.NetworkCallsManager

/**
 * used to call facts from server using network calls
 */
class RowViewModel : ViewModel() {
    fun getFacts(): MutableLiveData<Facts>? {
        return NetworkCallsManager().rows
    }

//    fun saveRows(context: Context, rows : List<Row>){
//        RowRepository(context).saveAllRows(rows as ArrayList<Row>)
//    }
//
//    fun getAllRows(context: Context){
//        RowRepository(context).getAllRows()
//    }
}