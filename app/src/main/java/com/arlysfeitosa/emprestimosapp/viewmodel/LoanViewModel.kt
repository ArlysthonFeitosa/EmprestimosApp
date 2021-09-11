package com.arlysfeitosa.emprestimosapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel
import com.arlysfeitosa.emprestimosapp.services.repository.Repository

class LoanViewModel(application: Application): AndroidViewModel(application) {

    private var mRepository = Repository(application.applicationContext)

    fun saveLoan(loan: LoanModel){
        //mRepository.saveLoan(loan)
    }
}