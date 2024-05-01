package com.example.innowisepexelstestapp.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.innowisepexelstestapp.di.module.AppCiceroneModule
import com.example.innowisepexelstestapp.di.module.AppViewModelModule
import com.example.innowisepexelstestapp.di.module.DataModuleDi
import com.example.innowisepexelstestapp.di.module.DomainModuleDi
import com.example.innowisepexelstestapp.presentation.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =
   [AppCiceroneModule::class,
    AppViewModelModule::class,
    DomainModuleDi::class,
    DataModuleDi::class]
)
interface AppComponent {

    @Component.Builder //todo хуйня какая-то)
    interface Builder {

        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)

    fun provideFactory(): ViewModelProvider.Factory
}