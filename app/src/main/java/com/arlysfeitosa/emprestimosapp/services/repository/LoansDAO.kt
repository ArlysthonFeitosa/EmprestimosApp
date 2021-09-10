package com.arlysfeitosa.emprestimosapp.services.repository

import androidx.room.*
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel

@Dao
interface LoansDAO {

    @Insert
    fun saveLoan(loan: LoanModel): Long

    @Update
    fun updateLoan(loan: LoanModel): Int

    @Delete
    fun deleteLoan(loan: LoanModel)

    @Query("SELECT * FROM loans")
    fun getAllLoans(): List<LoanModel>

    @Query("SELECT * FROM loans WHERE id = :id")
    fun getLoanById(id: Int): LoanModel

    @Query("SELECT id From loans WHERE date IN (:dateList)")
    fun selectIdWhereDateIn(dateList: List<String>): List<Int>

    @Query("SELECT * From loans WHERE id IN (:idList)")
    fun selectWhereIdIn(idList: List<Int>): List<LoanModel>

    @Query("DELETE FROM loans WHERE date NOT IN (:dateList)")
    fun deleteWhereNotIn(dateList: List<String>)

}