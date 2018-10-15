package gsihome.reyst.qc.ui.screens

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CalculatorView  : MvpView {
    fun showResult(formattedResult: String)
    fun showUnreachableResult()
}
