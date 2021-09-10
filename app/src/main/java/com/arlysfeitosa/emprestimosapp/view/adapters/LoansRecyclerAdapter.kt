package com.arlysfeitosa.emprestimosapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlysfeitosa.emprestimosapp.R
import com.arlysfeitosa.emprestimosapp.services.listeners.LoanListener
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel
import com.arlysfeitosa.emprestimosapp.view.viewholders.LoansRecyclerViewHolder
import java.lang.Exception

class LoansRecyclerAdapter : RecyclerView.Adapter<LoansRecyclerViewHolder>() {

    private var mList: List<LoanModel> = arrayListOf()
    private lateinit var mListener: LoanListener
    private var haveToNotify: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoansRecyclerViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_loan,
            parent, false)
        return LoansRecyclerViewHolder(item)
    }

    override fun onBindViewHolder(holder: LoansRecyclerViewHolder, position: Int) {
        val loan = mList[position]
        holder.bindData(loan)

        synchronized(this){
            holder.isReturned.setOnCheckedChangeListener{ _, isChecked ->
                if(isChecked){
                    if(loan.isReturned == false){
                        mListener.onCompleteClick(loan.id)
                        try{
                            notifyItemChanged(holder.adapterPosition)
                        }catch (e:Exception){
                            mListener.onUndoClick(loan.id)
                        }
                    }
                    else{
                        holder.isReturned.isChecked = true
                    }
                } else if(!isChecked){
                    if(loan.isReturned == true){
                        mListener.onUndoClick(loan.id)
                        try{
                            notifyItemChanged(holder.adapterPosition)
                        }catch (e:Exception){
                            mListener.onCompleteClick(loan.id)
                        }
                    }
                    else{
                        holder.isReturned.isChecked = false
                    }
                }
            }

            holder.itemView.setOnLongClickListener{
                if(mListener.onDeleteClick(loan.id)){
                    notifyItemRemoved(holder.adapterPosition)
                }
                true
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: LoanListener){
        this.mListener = listener
        val loan = LoanModel()
        loan.id = 0
        loan.date = "00/00/0000"
        loan.finalClass = "3"
        loan.initialClass = "1"
        loan.forWhom = "Arlysthon"
        loan.isReturned = true
        loan.objectToLoan = "Notebook 10"

        this.mList = arrayListOf(loan)
    }

    fun updateList(list: List<LoanModel>): Boolean{
        if(mList.size != list.size){
            haveToNotify = true
        }

        mList = list

        if(haveToNotify){
            try{
                synchronized(this){
                    notifyDataSetChanged()
                    haveToNotify = false
                }
            }catch (e: Exception){
                return false
            }
        }
        return true
    }
}