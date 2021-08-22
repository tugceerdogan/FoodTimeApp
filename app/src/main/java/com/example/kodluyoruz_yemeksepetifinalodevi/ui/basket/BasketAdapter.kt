package com.example.kodluyoruz_yemeksepetifinalodevi.ui.basket


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kodluyoruz_yemeksepetifinalodevi.R
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.basket.Basket
import java.text.SimpleDateFormat

class BasketAdapter :
    RecyclerView.Adapter<BasketAdapter.OrderListViewHolder>() {
    var myOrderList = ArrayList<Basket>()

    class OrderListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var orderImageView: ImageView = view.findViewById(R.id.ordersImageView)
        private var orderRestaurantName: TextView =
            view.findViewById(R.id.ordersItemRestaurantNameTextView)
        private var orderDate: TextView = view.findViewById(R.id.ordersItemDateTextView)
        private var orderMealName: TextView = view.findViewById(R.id.ordersItemFoodNameTextView)
        private var orderPrice: TextView = view.findViewById(R.id.orderPriceTextView)

        @SuppressLint("SimpleDateFormat")
        fun setItem(basket: Basket) {
            Glide.with(orderImageView.context)
                .load(basket.meal.imageUrl)
                .into(orderImageView)
            orderRestaurantName.text = basket.restaurant.name
            orderMealName.text = basket.meal.name
            orderDate.text = SimpleDateFormat("dd/MM/yyyy").format(basket.createdDate).toString()
            orderPrice.text = basket.meal.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_baskets, parent, false)
        return OrderListViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val item = myOrderList[position]
        holder.setItem(item)

    }

    fun setBasketList(list: ArrayList<Basket>) {
        this.myOrderList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = myOrderList.size
}