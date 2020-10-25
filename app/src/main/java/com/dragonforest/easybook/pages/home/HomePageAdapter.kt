package com.dragonforest.easybook.pages.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 *
 * create by DragonForest at 2020/10/25
 */
class HomePageAdapter : FragmentStatePagerAdapter {
    constructor(fm: FragmentManager) : super(fm) {}

    private var titles = arrayListOf<String>()
    private var fragments = arrayListOf<Fragment>()

    /**
     * Return the Fragment associated with a specified position.
     */
    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun addItem(title: String, fg: Fragment) {
        titles.add(title)
        fragments.add(fg)
        notifyDataSetChanged()
    }
}