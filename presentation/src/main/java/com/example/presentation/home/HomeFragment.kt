package com.example.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.presentation.R
import com.example.presentation.adapter.GlobalTransactionsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var transactionAdapter: GlobalTransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.ratesList.observe(this.viewLifecycleOwner, Observer {
            Log.i("Inf", it.toString()                                                                                                                                        )
        })
        homeViewModel.transactionsList.observe(this.viewLifecycleOwner, Observer {
            Log.i("Inf2", it.toString())
            transactionAdapter = GlobalTransactionsAdapter(it)
            transactionAdapter.notifyDataSetChanged()
        })
    }
}