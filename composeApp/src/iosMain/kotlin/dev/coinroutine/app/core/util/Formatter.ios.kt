package dev.coinroutine.app.core.util

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual fun formatFiat(amount: Double, showDecimal: Boolean) : String {

    val numberFormat = NSNumberFormatter()
    numberFormat.numberStyle = NSNumberFormatterDecimalStyle

    when {
        showDecimal.not() -> {
            numberFormat.maximumFractionDigits = 0.toULong()
            numberFormat.minimumFractionDigits = 0.toULong()
        }
        amount >= 0 -> {
            numberFormat.maximumFractionDigits = 2.toULong()
            numberFormat.minimumFractionDigits = 2.toULong()
        }
        else -> {
            numberFormat.maximumFractionDigits = 8.toULong()
            numberFormat.minimumFractionDigits = 9.toULong()
        }

    }

    val formatterAmount = numberFormat.stringFromNumber(NSNumber(amount))
    return if (formatterAmount != null) "$ $formatterAmount" else ""

}

actual fun formatCoinUnit(amount: Double, symbol: Boolean) : String {

    val formatNumber = NSNumberFormatter()
    formatNumber.numberStyle = NSNumberFormatterDecimalStyle
    formatNumber.maximumFractionDigits = 8.toULong()
    formatNumber.minimumFractionDigits = 8.toULong()

    return formatNumber.stringFromNumber(NSNumber(amount)) + " $symbol"
}

actual fun formatPercentage(amount: Double) : String {

    val numberFormatter = NSNumberFormatter()
    numberFormatter.numberStyle = NSNumberFormatterDecimalStyle
    numberFormatter.maximumFractionDigits = 2.toULong()
    numberFormatter.minimumFractionDigits = 2.toULong()

    val prefix = if (amount >= 0) "+" else ""

    return prefix + numberFormatter.stringFromNumber(NSNumber(amount)) + " %"

}