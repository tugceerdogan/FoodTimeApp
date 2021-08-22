package com.example.kodluyoruz_yemeksepetifinalodevi.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> FirstOnboardingFragment()
            1 -> SecondOnboardingFragment()
            else -> ThirdOnboardingFragment()
        }
    }
}