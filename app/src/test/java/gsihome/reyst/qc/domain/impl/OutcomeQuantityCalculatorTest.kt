package gsihome.reyst.qc.domain.impl

import gsihome.reyst.qc.domain.QuantityCalculator
import gsihome.reyst.qc.domain.UnreachableValue
import gsihome.reyst.qc.domain.impl.OutcomeQuantityCalculator
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OutcomeQuantityCalculatorTest {

    private lateinit var calculator: QuantityCalculator

    @Before
    fun setUp() {
        calculator = OutcomeQuantityCalculator()
    }

    @Test
    fun `Should return value equals outcome`() {
        val zeroLoss = 0.toDouble()
        val outcome = 1000.toDouble()
        val result = calculator.calcQuantity(zeroLoss, outcome)
        Assert.assertEquals(outcome, result, 0.0001)
    }

    @Test
    fun `Should return 1005,03 because percent of loss = 0,5 and outcome = 1000`() {
        val zeroLoss = 0.5
        val outcome = 1000.toDouble()
        val result = calculator.calcQuantity(zeroLoss, outcome)
        Assert.assertEquals(1005.03, result, 0.01)
    }

    @Test
    fun `Should return 1003,51 because percent of loss = 0,35 and outcome = 1000`() {
        val zeroLoss = 0.35
        val outcome = 1000.toDouble()
        val result = calculator.calcQuantity(zeroLoss, outcome)
        Assert.assertEquals(1003.51, result, 0.01)
    }

    @Test(expected = UnreachableValue::class)
    fun `Should throws UnreachableValue exception`() {
        val zeroLoss = 100.toDouble()
        val outcome = 1000.toDouble()
        val result = calculator.calcQuantity(zeroLoss, outcome)
    }
}