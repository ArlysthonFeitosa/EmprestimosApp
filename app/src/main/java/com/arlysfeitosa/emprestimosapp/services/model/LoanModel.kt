package com.arlysfeitosa.emprestimosapp.services.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Loans")
class LoanModel{

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "forWhom")
    var forWhom: String = ""

    @ColumnInfo(name = "objectToLoan")
    var objectToLoan: String = ""

    @ColumnInfo(name = "date")
    var date: String = ""

    @ColumnInfo(name = "initialClass")
    var initialClass: String = ""

    @ColumnInfo(name = "finalClass")
    var finalClass: String = ""

}