package com.example.innowisepexelstestapp.presentation.navigation

import com.example.innowisepexelstestapp.presentation.view.DetailsFragment
import com.example.innowisepexelstestapp.presentation.view.FavoriteFragment
import com.example.innowisepexelstestapp.presentation.view.HomeFragment
import com.example.innowisepexelstestapp.presentation.view.SplashScreenFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun homeFragment() = FragmentScreen { HomeFragment() }
    fun favoriteFragment() = FragmentScreen { FavoriteFragment() }
    fun splashScreenFragment() = FragmentScreen { SplashScreenFragment() }
    fun detailsFragment() = FragmentScreen { DetailsFragment() }
}