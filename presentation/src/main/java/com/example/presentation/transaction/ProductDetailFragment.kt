package com.example.presentation.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.adapter.ProductAdapter
import com.example.presentation.adapter.TransactionsAdapter
import com.example.presentation.databinding.FragmentProductDetailBinding
import com.example.presentation.model.ProductUIModel


class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var productDetail: ProductUIModel
    private lateinit var transactionAdapter: TransactionsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if(bundle != null) {
            productDetail = bundle.getSerializable("product") as ProductUIModel
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setDataRecyclerView()
    }

    private fun setDataRecyclerView() {
        transactionAdapter = TransactionsAdapter(productDetail.transactions)
        transactionAdapter.notifyDataSetChanged()
        binding.rvTransactions.adapter = transactionAdapter
    }

    private fun initViews() {
        binding.rvTransactions.layoutManager = LinearLayoutManager(context)
        binding.tvSkuValue.text = productDetail.name
        binding.tvCurrencyValue.text = productDetail.currency
        binding.tvAmountValue.text = productDetail.amount
    }
}