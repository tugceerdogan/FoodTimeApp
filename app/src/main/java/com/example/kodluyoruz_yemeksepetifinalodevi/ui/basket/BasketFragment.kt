package com.example.kodluyoruz_yemeksepetifinalodevi.ui.basket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.BasketFragmentBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.util.Resource
import com.example.kodluyoruz_yemeksepetifinalodevi.util.gone
import com.example.kodluyoruz_yemeksepetifinalodevi.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : Fragment() {
    private var binding: BasketFragmentBinding? = null
    private val viewModel: BasketViewModel by viewModels()
    val adapter = BasketAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BasketFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBasket()
    }

    private fun getBasket() {
        viewModel.getBaskets().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> {
                    setLoading(true)
                }
                Resource.Status.SUCCESS -> {
                    response.data?.orderList?.let {
                        binding?.basketRecyclerView?.layoutManager = LinearLayoutManager(context)
                        binding?.basketRecyclerView?.adapter = adapter
                        adapter.setBasketList(it)

                        if(response.data?.orderList?.size ==0){

                            binding?.basketRecyclerView?.isVisible = false
                            binding?.dataEmpty?.isVisible = true


                        }
                        setLoading(false)
                    }

                }

                Resource.Status.ERROR -> {
                    println("${response.message}")
                    Log.v("basket", response.toString())
                    setLoading(false)
                }
            }
        })
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.basketProgressBar?.show()
            binding?.basketRecyclerView?.gone()
        } else {
            binding?.basketProgressBar?.gone()
            binding?.basketRecyclerView?.show()
        }
    }
}
