package gsihome.reyst.qc.ui

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import gsihome.reyst.qc.ui.screens.NameAwareFragment

class CalcPagerAdapter(
        private val fragments: Array<NameAwareFragment>,
        private val context: Context,
        manager: FragmentManager
) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int) = fragments[position]
    override fun getCount(): Int = fragments.size
    override fun getPageTitle(position: Int) = fragments[position].getFragmentName(context)
}
