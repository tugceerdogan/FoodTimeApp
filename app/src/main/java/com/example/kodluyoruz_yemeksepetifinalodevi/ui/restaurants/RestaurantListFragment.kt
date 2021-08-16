package com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.RestaurantListFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.FirstOfferFragment
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.SecondOfferFragment
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.ThirdOfferFragment
import com.example.kodluyoruz_yemeksepetifinalodevi.viewpager.ViewPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestaurantListFragment : Fragment() {

    private lateinit var _binding: RestaurantListFragmentBinding
    private val viewModel: RestaurantListViewModel by viewModels()

    private val hospitalListAdapter = RestaurantListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RestaurantListFragmentBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchHospitalList().observe(viewLifecycleOwner, {
            //it.status bizim için bir state
            when (it.status) {
                //Bu 3 farklı state de artık ui'ı yönetebilir hale geldik
                Resource.Status.LOADING -> {
                    _binding.progressBar.show()
                }
                Resource.Status.SUCCESS -> {
                    _binding.progressBar.gone()
                    Log.v("HospitalList", "${it.data}")
                    hospitalListAdapter.setData(it.data)
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
        //Fragment List
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
        _binding.restaurantsRecyclerView.adapter = hospitalListAdapter
        _binding.restaurantsRecyclerView.layoutManager = LinearLayoutManager(context)

        /*hospitalListAdapter.setHospitalOnClickListener(object : IRestaurantClickListener {
            override fun onClick(name: RestaurantsItem) {

                Log.v("Error", "Error olmuyoooor")
                val action =
                    HospitalListFragmentDirections.actionHospitalListFragmentToHospitalDetailFragment(
                        name.name,
                        name.address
                    )
                findNavController().navigate(action)
            }
        })*/
    }

}