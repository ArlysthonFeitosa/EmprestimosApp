package com.arlysfeitosa.emprestimosapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.arlysfeitosa.emprestimosapp.R
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel
import com.arlysfeitosa.emprestimosapp.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.activity_loan.*

class LoanActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mViewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan)

        button_fazerEmprestimo.setOnClickListener(this)

        mViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        observer()
    }

    fun makeLoanModel(): LoanModel{
        val loan = LoanModel()
        loan.forWhom = edit_text_whom.text.toString()
        loan.objectToLoan = edit_text_object.text.toString()
        loan.initialClass = (spinner_emprestimo.selectedItemId.toInt() + 1).toString()
        loan.finalClass = (spinner_entrega.selectedItemId.toInt() + 1).toString()
        loan.date = "11/09/2021"

        return loan
    }

    private fun checkFields(): Boolean{
        return !(edit_text_object.text.toString() == "" || edit_text_whom.text.toString() == "")
    }


    fun observer(){

    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_fazerEmprestimo){
            if(checkFields()){
                mViewModel.saveLoan(makeLoanModel())
                finish()
            }
        }
    }
}