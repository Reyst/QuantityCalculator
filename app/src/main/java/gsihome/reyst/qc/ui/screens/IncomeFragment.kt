package gsihome.reyst.qc.ui.screens

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import gsihome.reyst.qc.R
import gsihome.reyst.qc.domain.DefaultResultFormatter
import gsihome.reyst.qc.domain.impl.IncomeQuantityCalculator

class IncomeFragment : CalcFragment() {

    companion object {
        const val PRESENTER_TAG = "income"
    }

    override val prefix: String
        get() = PRESENTER_TAG

    @InjectPresenter
    override lateinit var presenter: CalcPresenter

    @ProvidePresenter
    fun providePresenter() = CalcPresenter(IncomeQuantityCalculator(), DefaultResultFormatter())

    override fun configureResultCaption(resultCaption: TextView) {
        resultCaption.setText(R.string.str_outcome)
    }

    override fun configureAmountView(amountView: TextInputLayout) {
        amountView.hint = getString(R.string.str_income)
    }

    override fun getFragmentName(context: Context): String {
        return context.getString(R.string.str_income)
    }

}