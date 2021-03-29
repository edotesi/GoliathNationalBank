package com.example.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.R
import com.example.presentation.adapter.GlobalTransactionsAdapter
import com.example.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var transactionAdapter: GlobalTransactionsAdapter

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

        homeViewModel.globalTransactionList.observe(this.viewLifecycleOwner, Observer {
            Log.i("Hola", "1")
            transactionAdapter = GlobalTransactionsAdapter(it)
            transactionAdapter.notifyDataSetChanged()
            binding.rvGlobalTransactions.layoutManager = LinearLayoutManager(context)
            binding.rvGlobalTransactions.adapter = transactionAdapter
        })
    }

}