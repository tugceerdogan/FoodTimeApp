package com.example.kodluyoruz_yemeksepetifinalodevi.ui.meals.mealDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kodluyoruz_yemeksepetifinalodevi.R
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.BasketAddRequest
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.MealDetailFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.ui.meals.MealIngredientsAdapter
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MealDetailFragment : Fragment() {

    private val args: MealDetailFragmentArgs by navArgs()
    private val viewModel: MealDetailsViewModel by viewModels()

    private lateinit var _binding: MealDetailFragmentBinding

    private var adapter: MealIngredientsAdapter = MealIngredientsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MealDetailFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredientsDummy = ArrayList<String>()
        ingredientsDummy.add("ingredient1")
        ingredientsDummy.add("ingredient2")

        initViews()
        initListener()
    }

    private fun initViews() {
        viewModel.getMealDetails(args.mealId).observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.e("Loading", "loading")
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    setLoading(false)
                    val meal = it.data!!.data
                    viewModel.meal = meal
                    val options = RequestOptions().placeholder(R.mipmap.ic_launcher)
                    Glide.with(_binding.mealImageView.context)
                        .applyDefaultRequestOptions(options)
                        .load(meal.imageUrl).into(_binding.mealImageView)
                    _binding.mealNameTextView.text = meal.name
                    _binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
                    adapter.setIngredients(meal.ingredients)
                    _binding.ingredientsRecyclerView.adapter = adapter
                    _binding.priceTextView.text = meal.price


                }
                Resource.Status.ERROR -> {
                    setLoading(false)
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            _binding.progressBar.show()
            _binding.backButton.gone()
            _binding.mealImageView.gone()
            _binding.addBasketButton.gone()
            _binding.mealNameTextView.gone()
            _binding.totalLinearLayout.gone()

        } else {
            _binding.progressBar.gone()
            _binding.backButton.show()
            _binding.mealImageView.show()
            _binding.addBasketButton.show()
            _binding.mealNameTextView.show()
            _binding.totalLinearLayout.show()
        }
    }

    private fun initListener() {
        _binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }



        _binding.addBasketButton.setOnClickListener {
            val basketAddRequest = BasketAddRequest(args.restaurantId, args.mealId)
            viewModel.postOrder(basketAddRequest).observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.e("Loading", "loading")
                        setLoading(true)
                        _binding.ingredientsRecyclerView.gone()
                    }
                    Resource.Status.SUCCESS -> {
                        setLoading(false)
                        _binding.ingredientsRecyclerView.show()
                        findNavController().navigate(MealDetailFragmentDirections.actionMealDetailFragmentToBasketFragment())

                    }
                    Resource.Status.ERROR -> {
                        setLoading(false)
                        _binding.ingredientsRecyclerView.show()
                    }
                }
            })
        }

    }

}