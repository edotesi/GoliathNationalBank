package com.example.presentation.transaction.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.TransactionItemBinding
import com.example.presentation.model.TransactionUIModel

class TransactionsAdapter(private var transactionsList: ArrayList<TransactionUIModel>) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view =
            TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionsList[position])
    }

}