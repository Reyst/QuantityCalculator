package gsihome.reyst.qc.domain.impl

import gsihome.reyst.qc.domain.QuantityCalculator
import gsihome.reyst.qc.domain.UnreachableValue
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class IncomeQuantityCalculatorTest {

    private lateinit var calculator: QuantityCalculator

    @Before
    fun setUp() {
        calculator = IncomeQuantityCalculator()
    }

    @Test
    fun `Should return value equals income`() {
        val percentLoss = 0.toDouble()
        val income = 1000.toDouble()
        val result = calculator.calcQuantity(percentLoss, income)
        Assert.assertEquals(income, result, 0.0001)
    }

    @Test
    fun `Should return 1000 because percent of loss = 0,5 and income = 1005,03`() {
        val percentLoss = 0.5
        val income = 1005.03
        val result = calculator.calcQuantity(percentLoss, income)
        Assert.assertEquals(1000.toDouble(), result, 0.01)
    }

    @Test
    fun `Should return 1000 because percent of loss = 0,35 and income = 1003,51`() {
        val percentLoss = 0.35
        val income = 1003.51
        val result = calculator.calcQuantity(percentLoss, income)
        Assert.assertEquals(1000.toDouble(), result, 0.01)
    }

    @Test(expected = UnreachableValue::class)
    fun `Should throws UnreachableValue exception`() {
        val percentLoss = 101.toDouble()
        val income = 1000.toDouble()
        val result = calculator.calcQuantity(percentLoss, income)
    }
}