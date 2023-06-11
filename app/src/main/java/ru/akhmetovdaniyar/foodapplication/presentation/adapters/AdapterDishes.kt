package ru.akhmetovdaniyar.foodapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.akhmetovdaniyar.foodapplication.data.dishes.Dish
import ru.akhmetovdaniyar.foodapplication.databinding.AdapterDishesBinding
import ru.akhmetovdaniyar.foodapplication.presentation.adapters.AdapterDishes.*

class AdapterDishes (private val data:List<Dish>, private val onItemClick: (Dish) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val itemBinding: AdapterDishesBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(dish: Dish) {
            itemBinding.nameDish.text = dish.name
            Glide.with(itemBinding.root)
                .load(dish.image_url)
                .into(itemBinding.imageDish)
            itemBinding.buttonDish.setOnClickListener {
                onItemClick(dish)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDishes.ViewHolder {
        val itemBinding = AdapterDishesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AdapterDishes.ViewHolder, position: Int) {
        val dishPosition: Dish = data[position]
        holder.bind(dishPosition)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}