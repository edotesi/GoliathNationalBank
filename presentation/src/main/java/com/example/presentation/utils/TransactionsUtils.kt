package com.example.presentation.utils

import android.provider.Settings
import com.example.domain.model.TransactionLocal
import com.example.presentation.model.GlobalTransactionUIModel
import com.example.presentation.model.TransactionUIModel


fun getGlobalTransactionsAmount(transactions: ArrayList<TransactionLocal>): ArrayList<GlobalTransactionUIModel> {
    val transactionsAmountList: ArrayList<GlobalTransactionUIModel> = ArrayList()
    var transactionsBySku: Map<String, List<TransactionLocal>> =
        transactions.groupBy(TransactionLocal::sku)
    transactionsBySku.entries.forEach { transactions ->
        getGlobalTransactionsBySku(transactions.value, transactions.key)
    }
    return null
}


fun getGlobalTransactionsBySku(
    transactions: List<TransactionLocal>,
    transactionName: String
): GlobalTransactionUIModel {
    var globalTransaction: GlobalTransactionUIModel =
        GlobalTransactionUIModel(transactionName, 0L, "EUR", null)

    return globalTransaction
}