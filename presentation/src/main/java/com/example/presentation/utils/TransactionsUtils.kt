package com.example.presentation.utils

import com.example.domain.model.TransactionLocal
import com.example.presentation.model.GlobalTransactionUIModel
import com.example.presentation.model.TransactionUIModel


fun getGlobalTransactionsAmount(transactions: ArrayList<TransactionLocal>): ArrayList<GlobalTransactionUIModel> {
    val transactionsAmountList: ArrayList<GlobalTransactionUIModel> = ArrayList()
    var transactionsBySku: Map<String, List<TransactionLocal>> =
        transactions.groupBy(TransactionLocal::sku)
    transactionsBySku.entries.forEach { transactions ->
        transactionsAmountList.add(getGlobalTransactionsBySku(transactions.value, transactions.key))
    }
    return transactionsAmountList
}


fun getGlobalTransactionsBySku(
    transactions: List<TransactionLocal>,
    transactionName: String
): GlobalTransactionUIModel {
    var globalTransaction: GlobalTransactionUIModel =
        GlobalTransactionUIModel(transactionName, 0f, "EUR", null)
    var amount: Float = 0f
    transactions.forEach { transaction ->
        amount += transaction.amount
        globalTransaction.transactions?.add(
            TransactionUIModel(
                transaction.amount,
                transaction.currency
            )
        )
    }

    globalTransaction.amount = amount
    return globalTransaction
}