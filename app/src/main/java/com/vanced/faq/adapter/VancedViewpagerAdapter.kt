package com.vanced.faq.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.vanced.faq.ui.fragments.VancedCategoryFragment
import com.vanced.faq.ui.fragments.VancedPageFragment
import com.vanced.faq.ui.fragments.VancedPagesFragment

class VancedViewpagerAdapter(
    activity: AppCompatActivity,
    private val categories: HashMap<String, JsonArray<JsonObject>?>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VancedCategoryFragment.createInstance(categories)
            1 -> VancedPagesFragment()
            2 -> VancedPageFragment()
            else -> throw IllegalArgumentException("This page does not exist")
        }
    }
}