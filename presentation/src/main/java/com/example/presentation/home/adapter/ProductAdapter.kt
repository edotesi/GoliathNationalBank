package com.example.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ProductItemBinding
import com.example.presentation.model.ProductUIModel

class ProductAdapter(
    private var transactionsList: ArrayList<ProductUIModel>,
    private val clickListener: (ProductUIModel) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(transactionsList[position], clickListener)
    }

}