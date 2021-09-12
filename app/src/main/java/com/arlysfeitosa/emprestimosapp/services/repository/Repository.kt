package com.arlysfeitosa.emprestimosapp.services.repository

import android.content.Context
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel

class Repository(context: Context) {

    private val mLoansDataBase = LoansDataBase.getDataBase(context).loansDao()

    fun saveLoan(loan: LoanModel): Boolean {
        return mLoansDataBase.saveLoan(loan) > 0
    }

    fun updateloan(loan:LoanModel){
        mLoansDataBase.updateLoan(loan)
    }

    fun deleteLoan(loan: LoanModel){
        mLoansDataBase.deleteLoan(loan)
    }

    fun getAllLoans():List<LoanModel>{
        return mLoansDataBase.getAllLoans()
    }
;
    fun getLoanById(id:Int): LoanModel{
        return mLoansDataBase.getLoanById(id)
    }

    fun getLoansInIdList(list: List<Int>): List<LoanModel>{
        return mLoansDataBase.selectWhereIdIn(list)
    }

    fun getLoansIdInDateList(list: List<String>): List<Int>{
        return mLoansDataBase.selectIdWhereDateIn(list)
    }

    fun deleteWhereNotInDates(dateList: List<String>){
        mLoansDataBase.deleteWhereNotIn(dateList)
    }

}