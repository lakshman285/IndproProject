package com.example.indproproject.localDatabase

import android.content.Context
import com.example.indproproject.models.Row
import org.jetbrains.anko.doAsync

/**
 * This class is used as repository to get data from row table
 * @param context contains instance of an activity
 */
class RowRepository (private val context: Context) {

    private val rowDAO = AppLocalDatabase.getInstance(context).rowDAO()

    /**
     * to save a row
     * @param row row object to be saved
     * @param saveCall return the  saved row info
     */
    fun saveRow(row: Row, saveCall: (Long?) -> Unit) {
        doAsync {
            saveCall(rowDAO.saveRow(row))
        }
    }

    /**
     * to save list of rows at once
     * @param rows list of rows to be saved
     */
    fun saveAllRows(rows: ArrayList<Row>, saveAllCall: () -> Unit) {
        doAsync {
            rowDAO.saveAllRows(rows)
            saveAllCall()
        }
    }

    /**
     * This function is used for to get all rows
     * @param allRowsCall list of faqs
     */
    fun getAllRows(allRowsCall: (ArrayList<Row>) -> Unit) {
        doAsync {
            allRowsCall(rowDAO.getAllRows() as ArrayList<Row>)
        }
    }

    /**
     * to update a row
     * @param row the row object which is to be updated
     * @param updateCall returns when the row is updated
     */
    fun updateRow(row: Row, updateCall: (Int) -> Unit) {
        doAsync {
            updateCall(rowDAO.updateRow(row))
        }
    }

    /**
     * used to delete rows
     * @param deleteCountryRows contains deletion rows result call
     */
    fun deleteRows(deleteCountryRows: () -> Unit) {
        doAsync {
            rowDAO.deleteRows()
            deleteCountryRows()
        }
    }
}