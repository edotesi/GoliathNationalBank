package com.example.presentation.adapter.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.TransactionLocal
import com.example.presentation.R

class GlobalTransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(transaction: TransactionLocal) {
        itemView.findViewById<TextView>(R.id.tvTransactionName).text = transaction.sku
        itemView.findViewById<TextView>(R.id.tvTransactionAmount).text = transaction.amount
    }

}