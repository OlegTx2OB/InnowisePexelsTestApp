package com.example.innowisepexelstestapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.innowisepexelstestapp.di.module.AppCiceroneModule
import com.example.innowisepexelstestapp.di.module.AppViewModelModule
import com.example.innowisepexelstestapp.di.module.DataRoomModule
import com.example.innowisepexelstestapp.di.module.DataNetworkModule
import com.example.innowisepexelstestapp.di.module.DomainModule
import com.example.innowisepexelstestapp.presentation.view.HomeFragment
import com.example.innowisepexelstestapp.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
   [AppCiceroneModule::class,
    AppViewModelModule::class,
    DomainModule::class,
    DataNetworkModule::class,
    DataRoomModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun provideFactory(): ViewModelProvider.Factory

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
}