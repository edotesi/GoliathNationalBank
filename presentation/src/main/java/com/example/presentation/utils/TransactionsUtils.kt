package com.example.presentation.utils

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
suspend fun getProductsList(
    transactions: ArrayList<TransactionLocal>,
    rates: ArrayList<RateLocal>,
    userCurrency: String
): ArrayList<ProductUIModel> {
    val transactionsAmountList: ArrayList<ProductUIModel> = ArrayList()
    var transactionsBySku: Map<String, List<TransactionLocal>> =
        transactions.groupBy(TransactionLocal::sku)
    transactionsBySku.entries.forEach { transactions ->
        transactionsAmountList.add(
            getProductDetails(
                transactions.value,
                transactions.key,
                rates,
                userCurrency
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
suspend fun getProductDetails(
    transactions: List<TransactionLocal>,
    transactionName: String,
    rates: ArrayList<RateLocal>,
    userCurrency: String
): ProductUIModel {
    var amount = BigDecimal.ZERO

    transactions.forEach { transaction ->
        amount = amount.add(currencyConverter(rates, transaction, userCurrency))
    }
    amount = amount.setScale(2, BigDecimal.ROUND_HALF_EVEN)

    return ProductUIModel(
        transactionName,
        amount.toString(),
        userCurrency,
        transactionsLocalToTransactionsUI(transactions as ArrayList<TransactionLocal>)
    )
}

/**
 * Method used for convert one currency amount to the user currency.
 * Get the current transaction of the product and make the conversion from it currency to the
 * user currency.
 * @return BigDecimal as value of the currency conversion.
 * //Todo: Improve the algorithm method. Actually the conversions are correct, but if we've a difficult conversion rates
 * the method have a infinite loop. For skip that I tried to control it with a counter and when it is like the rates.size
 * the loop stops and skips that transaction.
 * Is not the best practise, but I can not for now resolve the issue and I think is better have an option to skip
 * the loop than have a crash or infinite loop.
 */
suspend fun currencyConverter(
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
    var counterForStopLoop: Int = 0

    if (userCurrency == transaction.currency) {
        currentAmount = currentAmount.add(transaction.amount.toBigDecimal())
    } else {
        while (!conversionFinished && counterForStopLoop <= rates.size) {
            counterForStopLoop += 1
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
                        break
                    }
                }
            }
        }
    }
    return currentAmount
}