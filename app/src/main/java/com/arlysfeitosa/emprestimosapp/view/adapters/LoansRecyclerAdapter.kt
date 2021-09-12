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

        synchronized(this){
            holder.isReturned.setOnCheckedChangeListener{ _, isChecked ->
                if(isChecked){
                    if(!loan.isReturned){
                        mListener.onCompleteClick(loan.id)
                        loan.isReturned = true
                        try{
                            notifyItemChanged(holder.adapterPosition)
                        }catch (e:Exception){
                            mListener.onUndoClick(loan.id)
                            loan.isReturned = false
                        }
                    }

                } else if(!isChecked){
                    if(loan.isReturned){
                        mListener.onUndoClick(loan.id)
                        loan.isReturned = false
                        try{
                            notifyItemChanged(holder.adapterPosition)
                        }catch (e:Exception){
                            mListener.onCompleteClick(loan.id)
                            loan.isReturned = true
                        }
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

        holder.bindData(loan)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    fun attachListener(listener: LoanListener){
        this.mListener = listener
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