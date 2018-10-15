package gsihome.reyst.qc.ui.screens

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class NameAwareFragment: MvpAppCompatFragment() {
    abstract fun getFragmentName(context: Context): String
}