package com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurantsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.Restaurants
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.RestaurantsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.ItemRestaurantsBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.listeners.IRestaurantClickListener

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder>() {

    private lateinit var restaurantList: List<RestaurantsItem>

    private var listener: IRestaurantClickListener? = null

    class RestaurantListViewHolder(val binding: ItemRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(RestaurantsItem: RestaurantsItem, listener: IRestaurantClickListener?) {
            binding.restaurantName.text = RestaurantsItem.name
            binding.restaurantAddress.text = RestaurantsItem.district
            Glide.with(binding.imageViewRestaurant.context).load(RestaurantsItem.image).into(binding.imageViewRestaurant)
            binding.itemRestaurantsCardView.setOnClickListener { listener?.onClick(RestaurantsItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListViewHolder {
        return RestaurantListViewHolder(
            ItemRestaurantsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestaurantListViewHolder, position: Int) {
        restaurantList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList!!.size
    }

    fun setData(newList: List<RestaurantsItem>?) {
        if (newList != null) {
            restaurantList = newList
        }
        notifyDataSetChanged()
    }

    fun setRestaurantOnClickListener(listener: IRestaurantClickListener) {
        this.listener = listener
    }

}