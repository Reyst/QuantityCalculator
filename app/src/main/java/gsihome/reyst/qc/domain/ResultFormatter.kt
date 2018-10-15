package gsihome.reyst.qc.domain

import java.util.*

interface ResultFormatter {
    fun format(value: Number, precision: Int = 2): String
}

class DefaultResultFormatter : ResultFormatter {
    override fun format(value: Number, precision: Int) =
            String.format(Locale.getDefault(), "%.${precision}f", value.toDouble())
}