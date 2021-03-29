package com.example.presentation.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TransactionLocal
import com.example.presentation.R
import com.example.presentation.databinding.GlobalTransactionItemBinding
import com.example.presentation.model.GlobalTransactionUIModel

class GlobalTransactionViewHolder(private val itemBinding: GlobalTransactionItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(transaction: GlobalTransactionUIModel) {
        itemBinding.tvGlobalTransactionName.text = transaction.name
        itemBinding.tvGlobalTransactionAmount.text = transaction.amount
        itemBinding.tvGlobalTransactionAmountName.text = transaction.amount
        itemBinding.tvGlobalTransactionCurrency.text = transaction.currency
    }
}