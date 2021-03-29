package com.example.presentation.utils

import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal
import com.example.presentation.model.GlobalTransactionUIModel
import com.example.presentation.model.mapper.transactionLocalToTransactionUI
import java.math.BigDecimal


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
        GlobalTransactionUIModel(transactionName, "", "EUR", null)
    transactions.forEach { transaction ->
        globalTransaction.transactions?.add(
            transactionLocalToTransactionUI(transaction)
        )
    }
    return globalTransaction
}

fun currencyConverter(rates: ArrayList<RateLocal>, transaction: TransactionLocal, userCurrency: String) {
    var amountConversion = BigDecimal.ZERO
    var currentAmount = BigDecimal.ZERO



    if (rates.find { it.to == userCurrency } != null) {
        var rate = rates.find { it.from == transaction.currency && it.to == userCurrency}?.rate as BigDecimal
        amountConversion = currentAmount.multiply(rate)
    }
}