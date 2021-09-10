package com.arlysfeitosa.emprestimosapp.services.listeners

import com.arlysfeitosa.emprestimosapp.services.model.LoanModel

interface LoanListener {
    fun onCompleteClick(id:Int)
    fun onUndoClick(id:Int)
    fun onDeleteClick(id: Int):Boolean
}