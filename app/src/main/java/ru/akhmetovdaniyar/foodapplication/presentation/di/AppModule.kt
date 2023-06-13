package ru.akhmetovdaniyar.foodapplication.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.akhmetovdaniyar.foodapplication.presentation.FirstViewModel

val appModule = module {

    viewModel<FirstViewModel> {
        FirstViewModel(get())
    }
}