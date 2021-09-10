package com.arlysfeitosa.emprestimosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.arlysfeitosa.emprestimosapp.R
import com.arlysfeitosa.emprestimosapp.viewmodel.LoanViewModel

class LoanActivity : AppCompatActivity() {

    private lateinit var mViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan)

        mViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        observer()
    }

    fun observer(){

    }
}