package com.example.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ProductItemBinding
import com.example.presentation.model.ProductUIModel
import com.example.presentation.utils.CurrencyEnum

class ProductViewHolder(private val itemBinding: ProductItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(transaction: ProductUIModel, clickListener: (ProductUIModel) -> Unit) {
        itemBinding.tvGlobalTransactionName.text = transaction.name
        itemBinding.tvGlobalTransactionAmount.text = transaction.amount
        itemBinding.tvGlobalTransactionAmountSymbol.text =
            CurrencyEnum.valueOf(transaction.currency).currencySymbol
        itemBinding.tvGlobalTransactionCurrency.text = transaction.currency
        itemBinding.cvGlobalTransaction.setOnClickListener { clickListener(transaction) }
    }

}