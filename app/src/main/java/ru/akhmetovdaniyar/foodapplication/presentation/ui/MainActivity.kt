package ru.akhmetovdaniyar.foodapplication.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.akhmetovdaniyar.foodapplication.R
import ru.akhmetovdaniyar.foodapplication.data.ApiService
import ru.akhmetovdaniyar.foodapplication.databinding.ActivityMainBinding
import ru.akhmetovdaniyar.foodapplication.presentation.FirstViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiService: ApiService by inject()
    private val myViewModel by viewModel<FirstViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(FragmentMainMenu())




        binding.bottomNavigationView.menu.findItem(R.id.search_bar).isCheckable = false
        binding.bottomNavigationView.menu.findItem(R.id.account_bar).isCheckable = false
        binding.bottomNavigationView.menu.findItem(R.id.search_bar).isEnabled = false
        binding.bottomNavigationView.menu.findItem(R.id.account_bar).isEnabled = false
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bar -> {
                    loadFragment(FragmentMainMenu())
                    true
                }

                R.id.basket_bar -> {
                    loadFragment(FragmentBasket())
                    true
                }

                else -> {
                    loadFragment(FragmentMainMenu())
                    true
                }
            }
        }
        binding.frameLayoutFragments
        binding.photoImage.setOnClickListener {
            val fragmentManager: FragmentManager = supportFragmentManager
            val dialog = DialogFutureFunctional()
            dialog.show(fragmentManager, "dialog")
        }

        apiService
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout_fragments,fragment)
        transaction.commit()
    }
}