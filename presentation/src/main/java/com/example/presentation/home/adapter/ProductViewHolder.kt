package com.example.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ProductItemBinding
import com.example.presentation.model.ProductUIModel
import com.example.presentation.utils.CurrencyEnum

class ProductViewHolder(private val itemBinding: ProductItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(transaction: ProductUIModel, clickListener: (ProductUIModel) -> Unit) {
        itemBinding.tvProductName.text = transaction.name
        itemBinding.tvProductAmount.text = transaction.amount
        itemBinding.tvProductAmountSymbol.text =
            CurrencyEnum.valueOf(transaction.currency).currencySymbol
        itemBinding.tvProductCurrency.text = transaction.currency
        itemBinding.cvProduct.setOnClickListener { clickListener(transaction) }
    }

}