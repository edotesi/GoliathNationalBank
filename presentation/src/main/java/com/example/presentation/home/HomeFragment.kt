package com.example.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.adapter.ProductAdapter
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.model.ProductUIModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var transactionAdapter: ProductAdapter
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

        homeViewModel.transactionsList.observe(this.viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                homeViewModel.getGlobalTransactions()
            }
        })

        homeViewModel.productList.observe(this.viewLifecycleOwner, Observer {
            transactionAdapter = ProductAdapter(it) {
                productUIModel: ProductUIModel -> onItemClicked(productUIModel)
            }
            transactionAdapter.notifyDataSetChanged()
            binding.rvGlobalTransactions.layoutManager = LinearLayoutManager(context)
            binding.rvGlobalTransactions.adapter = transactionAdapter
        })
    }

    private fun onItemClicked(productUIModel: ProductUIModel) {
        homeViewModel.goToProductDetail(productUIModel)
    }

}