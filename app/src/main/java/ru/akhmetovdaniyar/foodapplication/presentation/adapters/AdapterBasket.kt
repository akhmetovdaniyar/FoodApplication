package ru.akhmetovdaniyar.foodapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.akhmetovdaniyar.foodapplication.data.dishes.Dish
import ru.akhmetovdaniyar.foodapplication.databinding.AdapterBasketBinding
import ru.akhmetovdaniyar.foodapplication.presentation.adapters.AdapterBasket.*
import ru.akhmetovdaniyar.foodapplication.data.ShoppingCart

class AdapterBasket (private val data:MutableList<Dish>, private val onItemClick: () -> Unit): RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val itemBinding: AdapterBasketBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(dish: Dish) {
            itemBinding.nameDishBasket.text = dish.name
            itemBinding.priceDishBasket.text = dish.price.toString() + " Р"
            itemBinding.weightDishBasket.text = "· " + dish.weight.toString() + "г"
            itemBinding.dishCount.text = ShoppingCart.getProductQuantity(dish.name).toString()
            Glide.with(itemBinding.root)
                .load(dish.image_url)
                .into(itemBinding.imageDishBasket)
            itemBinding.buttonAdd.setOnClickListener {
                ShoppingCart.addProduct(dish)
                itemBinding.dishCount.text = ShoppingCart.getProductQuantity(dish.name).toString()
                onItemClick()
            }
            itemBinding.buttonSub.setOnClickListener {
                ShoppingCart.removeProduct(dish)
                itemBinding.dishCount.text = ShoppingCart.getProductQuantity(dish.name).toString()
                onItemClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBasket.ViewHolder {
        val itemBinding = AdapterBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AdapterBasket.ViewHolder, position: Int) {
        val dishPosition: Dish = data[position]
        holder.bind(dishPosition)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}