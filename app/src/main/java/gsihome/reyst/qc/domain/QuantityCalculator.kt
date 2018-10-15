package gsihome.reyst.qc.domain

interface QuantityCalculator {

    @Throws(UnreachableValue::class)
    fun calcQuantity(percentLoss: Double, amount: Double): Double
}
