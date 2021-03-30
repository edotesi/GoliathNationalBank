package com.example.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.adapter.ProductAdapter
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.model.ProductUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var productsAdapter: ProductAdapter
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        binding.rvGlobalTransactions.layoutManager = LinearLayoutManager(context)
    }

    private fun initViewModel() {
        homeViewModel.transactionsList.observe(this.viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                homeViewModel.getProducts()
            }
        })

        homeViewModel.productList.observe(this.viewLifecycleOwner, Observer {
            setDataRecyclerView(it)
        })
    }

    private fun setDataRecyclerView(it: ArrayList<ProductUIModel>) {
        productsAdapter = ProductAdapter(it) { productUIModel: ProductUIModel ->
            onItemClicked(productUIModel)
        }
        productsAdapter.notifyDataSetChanged()
        binding.rvGlobalTransactions.adapter = productsAdapter
    }

    private fun onItemClicked(productUIModel: ProductUIModel) {
        var bundle: Bundle = Bundle()
        bundle.putSerializable("product", productUIModel)
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment, bundle)
    }

}