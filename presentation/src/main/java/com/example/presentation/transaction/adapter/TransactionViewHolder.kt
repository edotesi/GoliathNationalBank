package com.example.presentation.transaction.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.TransactionItemBinding
import com.example.presentation.model.TransactionUIModel

class TransactionViewHolder(private val itemBinding: TransactionItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(transaction: TransactionUIModel) {
        itemBinding.tvTransactionAmountValue.text = transaction.amount
        itemBinding.tvTransactionCurrencyValue.text = transaction.currency
    }

}