package gsihome.reyst.qc.domain.impl

import gsihome.reyst.qc.domain.QuantityCalculator

abstract class BaseQuantityCalculator : QuantityCalculator {

    protected fun isValueIncorrect(result: Double) = result < 0 ||
            result == Double.POSITIVE_INFINITY ||
            result == Double.NEGATIVE_INFINITY ||
            result == Double.NaN
}