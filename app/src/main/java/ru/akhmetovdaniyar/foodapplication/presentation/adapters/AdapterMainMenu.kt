package ru.akhmetovdaniyar.foodapplication.presentation.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.akhmetovdaniyar.foodapplication.data.category.Сategory
import ru.akhmetovdaniyar.foodapplication.databinding.AdapterMainMenuBinding
import ru.akhmetovdaniyar.foodapplication.presentation.adapters.AdapterMainMenu.*

class AdapterMainMenu(private val data:List<Сategory>, private val onItemClick: (String) -> Unit): RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val itemBinding: AdapterMainMenuBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(categories: Сategory) {
            itemBinding.nameCategory.text = categories.name
            Glide.with(itemBinding.root)
                .load(Uri.parse(categories.image_url))
                .into(itemBinding.imageBackground)
            itemBinding.buttonChoice.setOnClickListener {
                onItemClick(categories.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = AdapterMainMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val сategoryPosition: Сategory = data[position]
        holder.bind(сategoryPosition)

    }
}