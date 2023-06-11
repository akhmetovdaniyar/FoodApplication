package ru.akhmetovdaniyar.foodapplication.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.akhmetovdaniyar.foodapplication.data.ApiService.Companion.apiService
import ru.akhmetovdaniyar.foodapplication.data.category.Menu
import ru.akhmetovdaniyar.foodapplication.data.dishes.Dishes
import ru.akhmetovdaniyar.foodapplication.data.ShoppingCart
import ru.akhmetovdaniyar.foodapplication.data.dishes.Dish

class FirstViewModel(application: Application): AndroidViewModel(application) {

    private var job: Job? = null

    private val mutableListCategory = MutableLiveData<Menu>()
    val listCategory: LiveData<Menu> = mutableListCategory

    private val mutableListDishes = MutableLiveData<Dishes>()
    val listDishes: LiveData<Dishes> = mutableListDishes

    val dateData= MutableLiveData<String>()
    val locationData = MutableLiveData<String>()

    val priceMutable: MutableLiveData<Int> = MutableLiveData(ShoppingCart.getTotalPrice())

    fun getCategories() {
        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = apiService?.getCategories()
            withContext(Dispatchers.Main) {
                mutableListCategory.value = response!!
            }
        }
    }

    fun getDishes() {
        job = CoroutineScope(Dispatchers.IO).launch(handler) {
            val response = apiService?.getDishes()
            withContext(Dispatchers.Main) {
                mutableListDishes.value = response!!
            }
        }
    }

    fun filteredDishes(tag: String): List<Dish> {
        val dishList: List<Dish> = mutableListDishes.value?.dishes ?: emptyList()
        val filteredList = dishList.filter { dish  ->
            tag in dish.tegs
        }
        return filteredList
    }

    private val handler = CoroutineExceptionHandler { _, _ ->
    }
}