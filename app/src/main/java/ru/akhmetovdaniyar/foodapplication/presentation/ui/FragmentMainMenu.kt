package ru.akhmetovdaniyar.foodapplication.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import ru.akhmetovdaniyar.foodapplication.R
import ru.akhmetovdaniyar.foodapplication.data.category.Сategory
import ru.akhmetovdaniyar.foodapplication.databinding.FragmentFirstMenuBinding
import ru.akhmetovdaniyar.foodapplication.presentation.FirstViewModel
import ru.akhmetovdaniyar.foodapplication.presentation.adapters.AdapterMainMenu
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FragmentMainMenu() : Fragment() {

    private var _binding: FragmentFirstMenuBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FirstViewModel by activityViewModels()

    private var listCategories: List<Сategory> = emptyList()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstMenuBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getCategories()

        val recyclerView: RecyclerView = binding.rvMainMenu
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        viewModel.listCategory.observe(viewLifecycleOwner) {

            listCategories = it.сategories
            recyclerView.adapter = AdapterMainMenu(listCategories) { name ->
                if (name == "Азиатская кухня") {
                    (navigateToAnotherFragment(name))
                } else {
                }
            }


        }


        //Дата
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("dd MMMM,yyyy", Locale("RU"))
        binding.textDate.text = dateFormat.format(currentDate)
        viewModel.dateData.value = dateFormat.format(currentDate)

        //Геолокация
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()

        binding.textDate.text = viewModel.dateData.value
        binding.textGps.text = viewModel.locationData.value


        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchLocation() {
        val task = fusedLocationClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }
        task.addOnSuccessListener {
            if (it != null) {
                val geocoder2 = Geocoder(
                    requireContext(),
                    Locale.Builder().setLanguage("RU").setScript("Cyrl").setRegion("RS").build()
                )
                try {
                    val addresses: List<Address>? = geocoder2.getFromLocation(
                        it.latitude,
                        it.longitude,
                        1
                    )
                    if (!addresses.isNullOrEmpty()) {
                        val address: Address = addresses[0]
                        viewModel.locationData.value = address.locality
                        binding.textGps.text = address.locality
                    } else {
                        binding.textGps.text = "Выберите город"
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun navigateToAnotherFragment(name: String) {
        val fragmentManager = parentFragmentManager
        val fragment = FragmentCategoryMenu(name)

        fragmentManager.beginTransaction()
            .replace(R.id.frame_layout_fragments, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}