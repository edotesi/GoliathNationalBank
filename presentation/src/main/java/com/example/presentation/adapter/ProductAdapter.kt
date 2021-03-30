package com.example.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.adapter.holder.ProductViewHolder
import com.example.presentation.databinding.GlobalTransactionItemBinding
import com.example.presentation.model.ProductUIModel

class ProductAdapter(private var transactionsList: ArrayList<ProductUIModel>, val clickListener: (ProductUIModel) -> Unit) : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = GlobalTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(transactionsList[position], clickListener)
    }


}