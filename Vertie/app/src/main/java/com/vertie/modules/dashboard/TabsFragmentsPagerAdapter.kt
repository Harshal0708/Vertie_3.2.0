package com.vertie.modules.dashboard

import androidx.fragment.app.*
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsFragmentsPagerAdapter(
    private val fragmentActivity: FragmentActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragments.count()
    override fun createFragment(position: Int) = fragments[position]
}
