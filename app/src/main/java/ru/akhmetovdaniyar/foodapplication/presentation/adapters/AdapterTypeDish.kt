package ru.akhmetovdaniyar.foodapplication.presentation.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import ru.akhmetovdaniyar.foodapplication.databinding.AdapterTypeDishesBinding

class AdapterTypeDish(private val data:List<String>, private val onItemClick: (String) -> Unit): RecyclerView.Adapter<AdapterTypeDish.ViewHolder>() {

    private var selectedItemPosition = 0

    inner class ViewHolder(private val itemBinding: AdapterTypeDishesBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(typeDish: String) {
            itemBinding.radioTypeButton.text = typeDish
            itemBinding.radioTypeButton.isChecked = bindingAdapterPosition  == selectedItemPosition
            itemBinding.radioTypeButton.setTextColor(
                if (itemBinding.radioTypeButton.isChecked) Color.WHITE
                else Color.BLACK
            )
            itemBinding.radioTypeButton.setOnCheckedChangeListener(null)
            itemBinding.radioTypeButton.setOnCheckedChangeListener  { _: CompoundButton?, isChecked: Boolean ->
                if (isChecked) {
                    val previousSelectedItemPosition = selectedItemPosition
                    selectedItemPosition = bindingAdapterPosition
                    notifyItemChanged(previousSelectedItemPosition)
                    notifyItemChanged(selectedItemPosition)
                    onItemClick(typeDish)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = AdapterTypeDishesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val typeDish: String = data[position]
        holder.bind(typeDish)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}