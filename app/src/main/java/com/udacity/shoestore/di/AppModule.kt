package com.udacity.shoestore.di

import com.udacity.shoestore.login.LoginViewModel
import com.udacity.shoestore.shoe_list.ShoeListViewModel
import com.udacity.shoestore.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { SplashViewModel() }
    viewModel { ShoeListViewModel() }
}
