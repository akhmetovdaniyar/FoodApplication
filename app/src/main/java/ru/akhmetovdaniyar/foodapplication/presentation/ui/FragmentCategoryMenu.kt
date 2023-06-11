package ru.akhmetovdaniyar.foodapplication.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.akhmetovdaniyar.foodapplication.R
import ru.akhmetovdaniyar.foodapplication.data.dishes.Dish
import ru.akhmetovdaniyar.foodapplication.databinding.FragmentSecondMenuBinding
import ru.akhmetovdaniyar.foodapplication.presentation.FirstViewModel
import ru.akhmetovdaniyar.foodapplication.presentation.adapters.AdapterDishes
import ru.akhmetovdaniyar.foodapplication.presentation.adapters.AdapterTypeDish


class FragmentCategoryMenu(nameCategory: String) : Fragment() {

    private var _binding: FragmentSecondMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirstViewModel by activityViewModels()

    private var setDish: Set<String> = emptySet()
    private var listDish: List<Dish> = emptyList()
    private var listDishes: List<String> = emptyList()

    val name = nameCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.nameCategory.text = name

        val layoutManagerHorizontal =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.getDishes()

        val recyclerViewHorizontal = binding.dishesMenu
        val recyclerViewDishes = binding.dishes

        recyclerViewHorizontal.setHasFixedSize(true)
        recyclerViewHorizontal.layoutManager = layoutManagerHorizontal
        recyclerViewDishes.layoutManager = GridLayoutManager(context, 3)


        viewModel.listDishes.observe(viewLifecycleOwner) { dishes ->

            listDish = dishes.dishes
            setDish = listDish.map { it.tegs }.flatten().toSet()
            listDishes = setDish.toList()
            recyclerViewHorizontal.adapter = AdapterTypeDish(listDishes) { name ->
                listDish = viewModel.filteredDishes(name)
                recyclerViewDishes.adapter = AdapterDishes(listDish) { dish ->
                    val dialog = DialogMenuFragment(dish)
                    dialog.show(parentFragmentManager, "dialog")
                }
            }
            recyclerViewDishes.adapter = AdapterDishes(listDish) { dish ->
                val dialog = DialogMenuFragment(dish)
                dialog.show(parentFragmentManager, "dialog")
            }

        }

        binding.buttonBack.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout_fragments, FragmentMainMenu())
                .addToBackStack(null)
                .commit()
        }

        super.onViewCreated(view, savedInstanceState)
    }
}