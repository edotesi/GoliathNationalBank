package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TransactionLocal
import com.example.presentation.R
import com.example.presentation.adapter.holder.GlobalTransactionViewHolder
import com.example.presentation.databinding.GlobalTransactionItemBinding
import javax.inject.Inject

class GlobalTransactionsAdapter(private var transactionsList: ArrayList<TransactionLocal>) : RecyclerView.Adapter<GlobalTransactionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlobalTransactionViewHolder {
        val view = GlobalTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GlobalTransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    override fun onBindViewHolder(holder: GlobalTransactionViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }


}