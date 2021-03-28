package com.example.presentation.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TransactionLocal
import com.example.presentation.R
import com.example.presentation.databinding.GlobalTransactionItemBinding

class GlobalTransactionViewHolder(private val itemBinding: GlobalTransactionItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(transaction: TransactionLocal) {
        itemBinding.tvTransactionName.text = transaction.sku
        itemBinding.tvTransactionAmount.text = transaction.amount
    }

}