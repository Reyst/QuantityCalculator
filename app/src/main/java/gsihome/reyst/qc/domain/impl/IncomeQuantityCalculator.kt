package gsihome.reyst.qc.domain.impl

import gsihome.reyst.qc.domain.UnreachableValue

class IncomeQuantityCalculator : BaseQuantityCalculator() {
    @Throws(UnreachableValue::class)
    override fun calcQuantity(percentLoss: Double, amount: Double): Double {
        val result = amount * (1.toDouble() - percentLoss / 100.0)
        if (isValueIncorrect(result)) throw UnreachableValue()
        return result
    }
}