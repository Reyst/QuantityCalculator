package gsihome.reyst.qc.ui.screens

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import gsihome.reyst.qc.domain.QuantityCalculator
import gsihome.reyst.qc.domain.ResultFormatter
import gsihome.reyst.qc.domain.UnreachableValue

@InjectViewState
class CalcPresenter(
        private val calculator: QuantityCalculator,
        private val formatter: ResultFormatter
) : MvpPresenter<CalculatorView>() {

    fun updateResult(percentLoss: Double, amount: Double, precision: Int) {
        try {
            val incomeQuantity = calculator.calcQuantity(percentLoss = percentLoss, amount = amount)
            val formattedResult = formatter.format(value = incomeQuantity, precision = precision)
            viewState.showResult(formattedResult)
        } catch (error: UnreachableValue) {
            viewState.showUnreachableResult()
        }
    }
}
