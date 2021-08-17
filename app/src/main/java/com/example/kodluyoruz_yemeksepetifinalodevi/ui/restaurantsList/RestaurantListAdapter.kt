package com.example.kodluyoruz_yemeksepetifinalodevi.ui.restaurantsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.Restaurants
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.restaurant.RestaurantsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.ItemRestaurantsBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.listeners.IRestaurantClickListener

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

    var restaurantList: Restaurants? = null

    private var listener: IRestaurantClickListener? = null

    class RestaurantViewHolder(val binding: ItemRestaurantsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(RestaurantsItem: RestaurantsItem, listener: IRestaurantClickListener?) {
            binding.restaurantName.text = RestaurantsItem.name
            binding.restaurantAddress.text = RestaurantsItem.address
            binding.restaurantScore.text = RestaurantsItem.score
            binding.itemRestaurantsCardView.setOnClickListener { listener?.onClick(RestaurantsItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            ItemRestaurantsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        restaurantList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList!!.size
    }

    fun setData(newList: Restaurants?) {
        restaurantList = newList
        notifyDataSetChanged()
    }

    fun setRestaurantOnClickListener(listener: IRestaurantClickListener) {
        this.listener = listener
    }

}