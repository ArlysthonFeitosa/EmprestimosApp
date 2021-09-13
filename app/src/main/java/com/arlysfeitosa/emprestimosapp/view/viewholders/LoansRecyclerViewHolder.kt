package com.arlysfeitosa.emprestimosapp.view.viewholders

import android.annotation.SuppressLint
import android.view.View
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arlysfeitosa.emprestimosapp.R
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel

class LoansRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private var mObject: TextView = itemView.findViewById(R.id.textview_object)
    private var mForWhom: TextView = itemView.findViewById(R.id.textView_forwhom)
    private var mPeriod: TextView = itemView.findViewById(R.id.textview_period)

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    var isReturned: Switch = itemView.findViewById(R.id.switch_returned)

    private var mLoanId: Int = 0

    fun bindData(loan: LoanModel){
        this.mObject.text = loan.objectToLoan
        this.mForWhom.text = loan.forWhom
        this.mPeriod.text = "${loan.initialClass}ª - ${loan.finalClass}ª"
        this.isReturned.isChecked = loan.isReturned
        mLoanId = loan.id
    }
}