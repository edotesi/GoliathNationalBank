package com.example.presentation.utils

import android.util.Log
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal
import com.example.presentation.model.ProductUIModel
import com.example.presentation.model.mapper.transactionsLocalToTransactionsUI
import java.math.BigDecimal


fun getGlobalTransactionsAmount(
    transactions: ArrayList<TransactionLocal>,
    rates: ArrayList<RateLocal>
): ArrayList<ProductUIModel> {
    val transactionsAmountList: ArrayList<ProductUIModel> = ArrayList()
    var transactionsBySku: Map<String, List<TransactionLocal>> =
        transactions.groupBy(TransactionLocal::sku)
    transactionsBySku.entries.forEach { transactions ->
        transactionsAmountList.add(
            getGlobalTransactionsBySku(
                transactions.value,
                transactions.key,
                rates
            )
        )
    }
    return transactionsAmountList
}


fun getGlobalTransactionsBySku(
    transactions: List<TransactionLocal>,
    transactionName: String,
    rates: ArrayList<RateLocal>
): ProductUIModel {
    var amount = BigDecimal.ZERO
    transactions.forEach { transaction ->
        amount = amount.add(currencyConverter(rates, transaction, "EUR"))
    }
    amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN)

    Log.i("Prueba", amount.toString())
    return ProductUIModel(
        transactionName,
        amount.toString(),
        "EUR",
        transactionsLocalToTransactionsUI(transactions as ArrayList<TransactionLocal>)
    )
}

fun currencyConverter(
    rates: ArrayList<RateLocal>,
    transaction: TransactionLocal,
    userCurrency: String
): BigDecimal {
    var currentAmount = BigDecimal.ZERO
    var auxAmount = 1.toBigDecimal()
    var actualCurrency: String = transaction.currency
    var conversionFinished = false
    var inversionRate: RateLocal = RateLocal("", "", "")
    var auxRatesList: ArrayList<RateLocal> = ArrayList()
    var lastCurrency: String = ""
    auxRatesList.addAll(rates)

    if (userCurrency == transaction.currency) {
        currentAmount = currentAmount.add(transaction.amount.toBigDecimal())
    } else {
        do {
            for (rate in auxRatesList.toList()) {
                if (actualCurrency == rate.from && !conversionFinished)  {
                    actualCurrency = rate.to
                    lastCurrency = rate.from
                    currentAmount = if (currentAmount.compareTo(BigDecimal.ZERO) == 0) {
                        auxAmount.multiply(rate.rate.toBigDecimal())
                            .multiply(transaction.amount.toBigDecimal())
                    } else {
                        currentAmount.multiply(rate.rate.toBigDecimal())
                    }
                    if (userCurrency == rate.to) {
                        conversionFinished = true
                    }
                    auxRatesList.forEach { rateInversion ->
                        if (rateInversion.from == rate.to && rateInversion.to == rate.from) {
                            inversionRate = rateInversion
                        }
                    }
                    //auxRatesList.remove(rate)
                }
            }
        } while (!conversionFinished)
    }
    return currentAmount
}