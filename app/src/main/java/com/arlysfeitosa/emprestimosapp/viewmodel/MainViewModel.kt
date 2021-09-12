package com.arlysfeitosa.emprestimosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel
import com.arlysfeitosa.emprestimosapp.services.repository.Repository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository = Repository(application.applicationContext)

    private var mAllLoans = MutableLiveData<List<LoanModel>>()
    val allLoans: LiveData<List<LoanModel>> = mAllLoans

    fun returnLoan(id: Int){
        val loan = getLoanById(id)
        loan.isReturned = true
        mRepository.updateloan(loan)
    }

    fun undoLoanReturn(id: Int){
        val loan = getLoanById(id)
        loan.isReturned = false
        mRepository.updateloan(loan)
    }

    fun deleteLoan(id: Int){
        mRepository.deleteLoan(getLoanById(id))
    }

    fun getLoanById(id: Int): LoanModel{
        return mRepository.getLoanById(id)
    }

    fun load(){
        mAllLoans.value = mRepository.getAllLoans()
    }
}