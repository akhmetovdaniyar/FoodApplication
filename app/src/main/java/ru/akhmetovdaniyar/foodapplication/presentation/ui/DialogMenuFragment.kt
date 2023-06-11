package ru.akhmetovdaniyar.foodapplication.presentation.ui

import android.app.Dialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import ru.akhmetovdaniyar.foodapplication.data.ShoppingCart
import ru.akhmetovdaniyar.foodapplication.data.dishes.Dish
import ru.akhmetovdaniyar.foodapplication.databinding.DialogMenuBinding

class DialogMenuFragment(private val dish: Dish): DialogFragment() {

    private var _binding: DialogMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogMenuBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)

        binding.nameDish.text = dish.name
        binding.price.text = dish.price.toString() + " Р"
        binding.weight.text = "· " + dish.weight.toString() + "г"
        binding.desc.movementMethod = ScrollingMovementMethod()
        binding.desc.text = dish.description
        Glide.with(binding.root)
            .load(dish.image_url)
            .into(binding.imageDish)

        binding.crossExit.setOnClickListener {
            dismiss()
        }

        binding.addBas.setOnClickListener {
            ShoppingCart.addProduct(dish)
        }

        return dialog
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}