package ru.akhmetovdaniyar.foodapplication.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.akhmetovdaniyar.foodapplication.R
import ru.akhmetovdaniyar.foodapplication.data.ApiService
import ru.akhmetovdaniyar.foodapplication.databinding.ActivityMainBinding
import ru.akhmetovdaniyar.foodapplication.presentation.FirstViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private val myViewModel: FirstViewModel by viewModels()

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
        ApiService.getInstance()

    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout_fragments,fragment)
        transaction.commit()
    }
}