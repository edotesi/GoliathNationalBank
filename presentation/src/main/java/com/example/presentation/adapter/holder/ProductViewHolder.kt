package com.example.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.GlobalTransactionItemBinding
import com.example.presentation.model.ProductUIModel

class ProductViewHolder(private val itemBinding: GlobalTransactionItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(transaction: ProductUIModel, clickListener: (ProductUIModel) -> Unit) {
        itemBinding.tvGlobalTransactionName.text = transaction.name
        itemBinding.tvGlobalTransactionAmount.text = transaction.amount
        itemBinding.tvGlobalTransactionAmountSymbol.text = "€"
        itemBinding.tvGlobalTransactionCurrency.text = transaction.currency
        itemBinding.cvGlobalTransaction.setOnClickListener{clickListener(transaction)}
    }
}