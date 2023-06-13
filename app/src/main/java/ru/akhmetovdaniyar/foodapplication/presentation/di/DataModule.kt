package ru.akhmetovdaniyar.foodapplication.presentation.di

import org.koin.dsl.module
import ru.akhmetovdaniyar.foodapplication.data.ApiService
import ru.akhmetovdaniyar.foodapplication.data.ShoppingCart

val dataModule = module {

    single<ApiService> {
        ApiService.getInstance()
    }

    single<ShoppingCart> {
        ShoppingCart
    }
}