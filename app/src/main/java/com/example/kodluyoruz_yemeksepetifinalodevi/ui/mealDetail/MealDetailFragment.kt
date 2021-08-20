package com.example.kodluyoruz_yemeksepetifinalodevi.ui.mealDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kodluyoruz_yemeksepetifinalodevi.R

class MealDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MealDetailFragment()
    }

    private lateinit var viewModel: MealDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meal_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MealDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}