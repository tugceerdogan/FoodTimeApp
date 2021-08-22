package com.example.kodluyoruz_yemeksepetifinalodevi.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kodluyoruz_yemeksepetifinalodevi.R




class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashFragmentDirections.actionSplashFragmentToOnboardingFragment()
            findNavController().navigate(action)
        }, 5000)
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }
}