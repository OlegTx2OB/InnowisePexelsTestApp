package com.example.innowisepexelstestapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innowisepexelstestapp.di.ViewModelFactory
import com.example.innowisepexelstestapp.di.ViewModelKey
import com.example.innowisepexelstestapp.presentation.viewmodel.DetailsViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.FavoriteViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.HomeViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.MainViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.SignInViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AppViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun detailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun favoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun signInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun signUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}