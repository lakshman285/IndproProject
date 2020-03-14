package com.example.indproproject.localDatabase

import androidx.room.*
import com.example.indproproject.models.Row

/*
 * This interface will be used as Dao class for the rows table,
 * in this interface we will write all data base queries.
 */
@Dao
interface RowDAO {

    /**
     * to save a row
     * @param row row object to be saved
     * @return returns when the row is saved
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRow(row: Row): Long?

    /**
     * to save list of rows at once
     * @param rows list of row to be saved
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRows(rows: List<Row>)

    /**
     * to get row based on the given category
     * @return returns the list of all rows
     */
    @Query("SELECT * FROM `Row`")
    fun getAllRows(): List<Row>

    /**
     * to update a given task
     * @param row the row to be updated
     * @return returns the updated row info
     */
    @Update
    fun updateRow(row: Row): Int

    /**
     * to delete the entire table
     */
    @Query("DELETE FROM `Row`")
    fun deleteRows()
}