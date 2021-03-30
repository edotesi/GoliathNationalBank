package com.example.presentation.model.mapper

import com.example.domain.model.TransactionLocal
import com.example.presentation.model.TransactionUIModel

/**
 * Method used to map transactionLocal data to transactionUI.
 * @return TransactionUIModel used later for print information in recyclerview.
 */
fun transactionsLocalToTransactionsUI(transactionsLocal: ArrayList<TransactionLocal>): ArrayList<TransactionUIModel> {
    var transactionsUi: ArrayList<TransactionUIModel> = ArrayList()
    transactionsLocal.forEach {
        transactionsUi.add(TransactionUIModel(it.amount, it.currency))
    }
    return transactionsUi
}