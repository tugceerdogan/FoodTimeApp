package com.example.kodluyoruz_yemeksepetifinalodevi.ui.meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.ItemMealsBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.listeners.IMealClickListener


class MealListAdapter: RecyclerView.Adapter<MealListAdapter.MealListViewHolder>() {
    private var mealList = ArrayList<MealsItem>()

    private var listener: IMealClickListener? = null

    class MealListViewHolder(val binding: ItemMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: MealsItem, listener: IMealClickListener?) {
            binding.TextViewName.text = meal.name
            binding.TextViewPrice.text = meal.price
            Glide.with(binding.imageViewMeal.context)
                .load(meal.imageUrl).into(binding.imageViewMeal)
            binding.itemFoodCardView.setOnClickListener { listener?.onClick(meal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        return MealListViewHolder(
            ItemMealsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        mealList?.get(position)?.let {
            holder.bind(it, listener)
        }
    }
    override fun getItemCount(): Int {
        return mealList.size
    }
    fun setMeals(newList: ArrayList<MealsItem>) {
        mealList = newList
        notifyDataSetChanged()
    }
    fun setMealOnClickListener(listener: IMealClickListener) {
        this.listener = listener
    }
}