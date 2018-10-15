package gsihome.reyst.qc.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import gsihome.reyst.qc.R
import gsihome.reyst.qc.databinding.ActivityMainBinding
import gsihome.reyst.qc.ui.screens.IncomeFragment
import gsihome.reyst.qc.ui.screens.OutcomeFragment

class MainActivity : MvpAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val pageAdapter = CalcPagerAdapter(arrayOf(OutcomeFragment(), IncomeFragment()), this, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.pager.adapter = pageAdapter
        binding.tabLayout.setupWithViewPager(binding.pager)
    }
}
