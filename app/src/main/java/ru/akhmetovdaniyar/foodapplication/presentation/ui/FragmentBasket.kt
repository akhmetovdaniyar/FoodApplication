package ru.akhmetovdaniyar.foodapplication.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.akhmetovdaniyar.foodapplication.data.ShoppingCart
import ru.akhmetovdaniyar.foodapplication.databinding.FragmentThirdBasketBinding
import ru.akhmetovdaniyar.foodapplication.presentation.FirstViewModel
import ru.akhmetovdaniyar.foodapplication.presentation.adapters.AdapterBasket

class FragmentBasket : Fragment() {

    private var _binding: FragmentThirdBasketBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirstViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBasketBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.textGps.text = viewModel.locationData.value
        binding.textDate.text = viewModel.dateData.value

        viewModel.priceMutable.observe(viewLifecycleOwner) {
            binding.buttonPay.text = "Оплатить $it Р"
        }

        val recyclerView = binding.recyclerBasket
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        recyclerView.adapter = AdapterBasket(ShoppingCart.getAllProducts()) {
            viewModel.priceMutable.value = ShoppingCart.getTotalPrice()
        }


        binding.buttonPay.setOnClickListener {
            val dialog = DialogFutureFunctional()
            dialog.show(parentFragmentManager, "dialog")
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        viewModel.priceMutable.value = ShoppingCart.getTotalPrice()
        super.onResume()
    }
}