package com.arlysfeitosa.emprestimosapp.services.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arlysfeitosa.emprestimosapp.services.model.LoanModel

@Database(entities = [LoanModel::class], version = 1)
abstract class LoansDataBase(): RoomDatabase() {

    abstract fun loansDao(): LoansDAO

    companion object{
        private lateinit var INSTANCE: LoansDataBase

        fun getDataBase(context: Context): LoansDataBase {
            if (::INSTANCE.isInitialized){
                synchronized(LoansDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        LoansDataBase::class.java,
                        "LoansDB"
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }
}