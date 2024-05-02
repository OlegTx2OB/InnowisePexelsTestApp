package com.example.innowisepexelstestapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innowisepexelstestapp.di.ViewModelFactory
import com.example.innowisepexelstestapp.di.ViewModelKey
import com.example.innowisepexelstestapp.presentation.viewmodel.DetailsViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.FavoriteViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.HomeViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.MainViewModel
import com.example.innowisepexelstestapp.presentation.viewmodel.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
interface AppViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class) //todo хз нахуя это. мб убрать, т.к. в оригинале чел использовал для цепи chains
    fun mainViewModel(viewModel: MainViewModel): ViewModel

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
    @ViewModelKey(SplashScreenViewModel::class)
    fun splashScreenViewModel(viewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun favoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}