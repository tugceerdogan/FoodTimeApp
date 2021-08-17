package com.example.kodluyoruz_yemeksepetifinalodevi.ui.searchRestaurant


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.RestaurantsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.SearchRestaurantFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.listeners.IRestaurantClickListener
import com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurantsList.RestaurantListAdapter
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint

class SearchRestaurantFragment : Fragment() {

    private lateinit var _binding: SearchRestaurantFragmentBinding
    private val viewModel: SearchRestaurantViewModel by viewModels()
    private val restaurantListAdapter = RestaurantListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchRestaurantFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job: Job?= null
        _binding.restaurantSearch.addTextChangedListener {searchEditable->
            job?.cancel()
            job = MainScope().launch {
                delay(200L)
                searchEditable?.let{
                    if(searchEditable.toString().isNotEmpty()){
                        viewModel.searchRestaurantList(searchEditable.toString()).observe(viewLifecycleOwner, {
                            when (it.status) {
                                Resource.Status.LOADING -> {
                                    _binding.progressBar.show()
                                }
                                Resource.Status.SUCCESS -> {
                                    _binding.progressBar.gone()
                                    Log.v("SearchList", "${it.data}")
                                    restaurantListAdapter.setData(it.data)
                                    initViews()

                                }
                                Resource.Status.ERROR -> {
                                    _binding.progressBar.gone()
                                }
                            }
                        })
                    }
                }
            }
        }

    }

    private fun initViews() {
        _binding.searchRestaurantsRecyclerView.adapter = restaurantListAdapter
        _binding.searchRestaurantsRecyclerView.layoutManager = LinearLayoutManager(context)

        restaurantListAdapter.setRestaurantOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: RestaurantsItem) {

                val action =
                    SearchRestaurantFragmentDirections.actionSearchRestaurantFragmentToRestaurantDetailFragment(
                        name.id
                    )
                findNavController().navigate(action)
            }
        })
    }

}
