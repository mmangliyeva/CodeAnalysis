fun outerFunction(income: Double): Double {
    if (income < 0.)
        return 0.
    val taxRate = 0.15

    fun innerFunction(inner_income: Double): Double {
        val taxFreeThreshold = 1000.0
        if (inner_income < taxFreeThreshold)
            return inner_income
        return inner_income  * taxRate
    }

    val baseTax = innerFunction1(income)
    if (baseTax > 100.) {
        val additionalTax = income * 0.05
        return baseTax + additionalTax
    }
    return baseTax
}