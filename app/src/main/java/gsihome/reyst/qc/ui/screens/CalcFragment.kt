package gsihome.reyst.qc.ui.screens

import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.ObservableField
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import gsihome.reyst.qc.R
import gsihome.reyst.qc.databinding.CalcScreenBinding

abstract class CalcFragment : NameAwareFragment(), CalculatorView {

    companion object {
        const val KEY_PRECISION = "precision"
        const val KEY_PERCENT_LOSS = "percent_loss"
        const val KEY_AMOUNT = "outcome"
    }

    private lateinit var binding: CalcScreenBinding

    private var preferences: SharedPreferences? = null

    private val precision = ObservableField<String>()
    private val percentLoss = ObservableField<String>()
    private val amount = ObservableField<String>()
    private val result = ObservableField<String>()

    abstract var presenter: CalcPresenter
    abstract val prefix: String

    private val onPropertyChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) = updateResult()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = context?.getSharedPreferences("history.preferences", Context.MODE_PRIVATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.calc_screen, container, false)

        binding.precision = precision
        binding.percent = percentLoss
        binding.amount = amount
        binding.result = result

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureAmountView(binding.tilAmount)
        configureResultCaption(binding.tvResultCaption)
    }

    private fun addListeners() {
        precision.addOnPropertyChangedCallback(onPropertyChangedCallback)
        percentLoss.addOnPropertyChangedCallback(onPropertyChangedCallback)
        amount.addOnPropertyChangedCallback(onPropertyChangedCallback)
    }

    private fun removeListeners() {
        precision.removeOnPropertyChangedCallback(onPropertyChangedCallback)
        percentLoss.removeOnPropertyChangedCallback(onPropertyChangedCallback)
        amount.removeOnPropertyChangedCallback(onPropertyChangedCallback)
    }

    abstract fun configureResultCaption(resultCaption: TextView)

    abstract fun configureAmountView(amountView: TextInputLayout)

    override fun onPause() {
        removeListeners()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        addListeners()
    }

    override fun onStop() {
        saveHistory()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        restoreHistory()
    }

    private fun updateResult() {
        val p = precision.get()?.toIntOrNull() ?: 0
        val l = percentLoss.get()?.toDoubleOrNull() ?: 0.toDouble()
        val a = amount.get()?.toDoubleOrNull() ?: 0.toDouble()
        presenter.updateResult(percentLoss = l, amount = a, precision = p)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_PRECISION, precision.get())
        outState.putString(KEY_PERCENT_LOSS, percentLoss.get())
        outState.putString(KEY_AMOUNT, amount.get())

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        precision.set(savedInstanceState?.getString(KEY_PRECISION, ""))
        percentLoss.set(savedInstanceState?.getString(KEY_PERCENT_LOSS, ""))
        amount.set(savedInstanceState?.getString(KEY_AMOUNT, ""))
    }

    override fun showResult(formattedResult: String) = result.set(formattedResult)

    override fun showUnreachableResult() = result.set(getString(R.string.unavailable_value))

    private fun saveHistory() {
        preferences?.run {
            edit().putString("$prefix.$KEY_PRECISION", precision.get())
                    .putString("$prefix.$KEY_PERCENT_LOSS", percentLoss.get())
                    .putString("$prefix.$KEY_AMOUNT", amount.get())
                    .apply()
        }
    }

    private fun restoreHistory() {
        preferences?.run {
            precision.set(this.getString("$prefix.$KEY_PRECISION", ""))
            percentLoss.set(this.getString("$prefix.$KEY_PERCENT_LOSS", ""))
            amount.set(this.getString("$prefix.$KEY_AMOUNT", ""))
        }
    }


}