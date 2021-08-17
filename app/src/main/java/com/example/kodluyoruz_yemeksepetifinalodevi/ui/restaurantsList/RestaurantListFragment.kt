package com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurantsList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.RestaurantsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.RestaurantListFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.listeners.IRestaurantClickListener
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.FirstOfferFragment
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.SecondOfferFragment
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.ThirdOfferFragment
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private lateinit var _binding: RestaurantListFragmentBinding
    private val viewModel: RestaurantListViewModel by viewModels()
    private val restaurantListAdapter = RestaurantListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantListFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchRestaurantList().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("HospitalList", "${it.data}")
                    restaurantListAdapter.setData(it.data)
                    initViews()
                }
                Resource.Status.ERROR -> {
                    _binding.progressBar.gone()
                }
            }
        })

        initViewPager()
    }
    private fun initViewPager() {
        val fragmentList = arrayListOf(
            FirstOfferFragment(),
            SecondOfferFragment(),
            ThirdOfferFragment()
        )
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        _binding.apply {
            viewPager.adapter = adapter
            dotsIndicator.setViewPager2(viewPager)
        }
    }

    private fun initViews() {
        _binding.restaurantsRecyclerView.adapter = restaurantListAdapter
        _binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: RestaurantsItem) {

                val action =
                    RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)
            }
        })
    }

}