package com.example.kodluyoruz_yemeksepetifinalodevi.ui.meals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.Meals
import com.example.kodluyoruz_yemeksepetifinalodevi.data.entity.meal.MealsItem
import com.example.kodluyoruz_yemeksepetifinalodevi.databinding.ItemMealsBinding
import com.example.kodluyoruz_yemeksepetifinalodevi.listeners.IMealClickListener


class MealListAdapter: RecyclerView.Adapter<MealListAdapter.MealListViewHolder>() {
    var mealList: Meals? = null

    private var listener: IMealClickListener? = null

    class MealListViewHolder(val binding: ItemMealsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MealsItem: MealsItem, listener: IMealClickListener?) {
            binding.mealName.text = MealsItem.name
            binding.mealPrice.text = MealsItem.price
            binding.itemFoodCardView.setOnClickListener { listener?.onClick(MealsItem) }
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
        return mealList!!.size
    }
    fun setData(newList: Meals?) {
        mealList = newList
        notifyDataSetChanged()
    }
    fun setMealOnClickListener(listener: IMealClickListener) {
        this.listener = listener
    }
}