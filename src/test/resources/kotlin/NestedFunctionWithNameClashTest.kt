fun innerFunction(income: Double): Double {
    if (income < 0.0)
        return 0.0
    if (income < 1000.0)
        return income
    return income * 0.15
}

fun outerFunction(income: Double): Double {
    if (income < 0.0)
        return 0.
    val taxRate = 0.15
    val taxFreeThreshold = 1000.0

    fun innerFunction(income: Double): Double {
        if (income < taxFreeThreshold)
            return income
        return income * taxRate
    }

    val baseTax = innerFunction(income)
    if (baseTax > 100.) {
        val additionalTax = income * 0.05
        return baseTax + additionalTax
    }
    return baseTax
}