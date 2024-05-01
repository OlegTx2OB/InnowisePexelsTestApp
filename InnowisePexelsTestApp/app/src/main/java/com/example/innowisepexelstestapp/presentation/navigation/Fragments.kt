package com.example.innowisepexelstestapp.presentation.navigation

import androidx.fragment.app.Fragment
import com.example.innowisepexelstestapp.presentation.view.FavoriteFragment
import com.example.innowisepexelstestapp.presentation.view.HomeFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class Fragments {
    object HomeScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return HomeFragment()
        }
    }

    object FavoriteScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return FavoriteFragment()
        }
    }
}