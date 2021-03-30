package com.example.presentation.utils

import android.util.Log
import com.example.domain.model.RateLocal
import com.example.domain.model.TransactionLocal
import com.example.presentation.model.ProductUIModel
import com.example.presentation.model.mapper.transactionsLocalToTransactionsUI
import java.math.BigDecimal

/**
 * Method used for get all the transactions grouped by sku.
 * @return ArrayList<ProductUIModel> where every product has the currency, sku name, a List of every
 * transaction with it sku and the amount.
 */
fun getProductsList(
    transactions: ArrayList<TransactionLocal>,
    rates: ArrayList<RateLocal>
): ArrayList<ProductUIModel> {
    val transactionsAmountList: ArrayList<ProductUIModel> = ArrayList()
    var transactionsBySku: Map<String, List<TransactionLocal>> =
        transactions.groupBy(TransactionLocal::sku)
    transactionsBySku.entries.forEach { transactions ->
        transactionsAmountList.add(
            getProductDetails(
                transactions.value,
                transactions.key,
                rates
            )
        )
    }
    return transactionsAmountList
}

/**
 * Method used for get amount of the product and transform transactions to UIModel for use it later
 * on RecyclerView.
 * @return ProductUIModel with all it data(Name, currency, amount, list of transactions)
 */
fun getProductDetails(
    transactions: List<TransactionLocal>,
    transactionName: String,
    rates: ArrayList<RateLocal>
): ProductUIModel {
    var amount = BigDecimal.ZERO

    transactions.forEach { transaction ->
        amount = amount.add(currencyConverter(rates, transaction, "EUR"))
    }
    amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN)

    return ProductUIModel(
        transactionName,
        amount.toString(),
        "EUR",
        transactionsLocalToTransactionsUI(transactions as ArrayList<TransactionLocal>)
    )
}

/**
 * Method used for convert one currency amount to the user currency.
 * Get the current transaction of the product and make the conversion from it currency to the
 * user currency.
 * @return BigDecimal as value of the currency conversion.
 */
fun currencyConverter(
    rates: ArrayList<RateLocal>,
    transaction: TransactionLocal,
    userCurrency: String
): BigDecimal {
    var currentAmount = BigDecimal.ZERO
    var auxAmount = 1.toBigDecimal()
    var actualCurrency: String = transaction.currency
    var conversionFinished = false
    var auxRatesList: ArrayList<RateLocal> = ArrayList()
    auxRatesList.addAll(rates)

    if (userCurrency == transaction.currency) {
        currentAmount = currentAmount.add(transaction.amount.toBigDecimal())
    } else {
        do {
            for (rate in auxRatesList.toList()) {
                if (actualCurrency == rate.from && !conversionFinished) {
                    actualCurrency = rate.to
                    currentAmount = if (currentAmount.compareTo(BigDecimal.ZERO) == 0) {
                        auxAmount.multiply(rate.rate.toBigDecimal())
                            .multiply(transaction.amount.toBigDecimal())
                    } else {
                        currentAmount.multiply(rate.rate.toBigDecimal())
                    }
                    if (userCurrency == rate.to) {
                        conversionFinished = true
                    }
                    //auxRatesList.remove(rate)
                }
            }
        } while (!conversionFinished)
    }
    return currentAmount
}