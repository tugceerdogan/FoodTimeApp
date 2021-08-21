package com.example.kodluyoruz_yemeksepetifinalodevi.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kodluyoruz_yemeksepetifinalodevi.R
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.FragmentOnboardingBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.sharedpref.SharedPreferencesManager
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.ViewPagerAdapter


class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val adapter = OnboardingAdapter(requireActivity())
        binding.onboardingViewPager.adapter = adapter
        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)

        binding.onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.prevButton.visibility = View.GONE
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem + 1
                    }
                } else if (position == 2) {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = "Finish"
                    binding.nextButton.setOnClickListener {
                        SharedPreferencesManager(requireContext()).setOnboardingSeen()
                        findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)

                    }
                } else {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = "Next"
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem + 1
                    }
                    binding.prevButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem - 1
                    }
                }
            }
        })

    }


}