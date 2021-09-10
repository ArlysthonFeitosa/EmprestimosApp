package com.arlysfeitosa.emprestimosapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arlysfeitosa.emprestimosapp.R
import com.arlysfeitosa.emprestimosapp.services.listeners.LoanListener
import com.arlysfeitosa.emprestimosapp.view.adapters.LoansRecyclerAdapter
import com.arlysfeitosa.emprestimosapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mListener: LoanListener
    private val mLoansAdapter = LoansRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_loanpage.setOnClickListener(this)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        loadListener()
        loadRecycler()
        observer()

        mViewModel.load()
    }


    fun loadListener(){
        mListener = object : LoanListener {
            override fun onCompleteClick(id: Int) {
                mViewModel.returnLoan(id)
            }

            override fun onUndoClick(id: Int) {
                mViewModel.undoLoanReturn(id)
            }

            override fun onDeleteClick(id: Int): Boolean {
                var deleted = false
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Apagar Empréstimo?")
                alert.setMessage("Esta ação não poderá ser desfeita.")
                alert.setPositiveButton("Deletar"){ _, _ ->
                    mViewModel.deleteLoan(id)
                    mViewModel.load()
                    deleted = true
                }
                alert.setNegativeButton("Cancelar", null)
                alert.show()
                return deleted
            }
        }
    }

    fun loadRecycler(){
        recycler_loans.layoutManager = LinearLayoutManager(this)
        recycler_loans.adapter = mLoansAdapter
        mLoansAdapter.attachListener(mListener)
    }

    override fun onClick(v: View) {
        if(v == button_loanpage){
            startActivity(Intent(applicationContext, LoanActivity::class.java))
        }
    }

    fun observer(){}

}