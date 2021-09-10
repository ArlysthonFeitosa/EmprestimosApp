package com.arlysfeitosa.emprestimosapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.arlysfeitosa.emprestimosapp.R
import com.arlysfeitosa.emprestimosapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_loanpage.setOnClickListener(this)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observer()
    }
    fun observer(){}

    override fun onClick(v: View) {
        if(v == button_loanpage){
            startActivity(Intent(applicationContext, LoanActivity::class.java))
        }
    }
}