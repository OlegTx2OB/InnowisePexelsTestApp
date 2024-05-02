package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.App
import com.example.innowisepexelstestapp.databinding.ActivityMainBinding
import com.example.innowisepexelstestapp.di.injectViewModel
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.example.innowisepexelstestapp.presentation.viewmodel.MainViewModel
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import java.lang.ref.WeakReference
import java.util.ArrayList
import javax.inject.Inject

//todo удалить эти заметки нахуй
//todo закинуть библы в каталоги

class MainActivity : AppCompatActivity() {
    private val mBinding by viewBinding(ActivityMainBinding::bind)
    private val mVm: MainViewModel by injectViewModel()

    val chain = ArrayList<WeakReference<Fragment>>()

    @Inject
    lateinit var mRouter: Router

    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.splashScreenFragment())))
            mVm.delayedToHomeScreen()
        }

        setupClickListeners()
        setupObservers()
    }

    private fun setupClickListeners() = with(mBinding) {
        bnvHome.setOnClickListener {
            mVm.toHomeScreen()
            bnvHomeBtn.setImageResource(R.drawable.ic_home_active)
            bnvFavoriteBtn.setImageResource(R.drawable.ic_favorite_inactive)
            bnvFavoriteIndicator.visibility = View.INVISIBLE
            bnvHomeIndicator.visibility = View.VISIBLE
        }
        bnvFavorite.setOnClickListener {
            mVm.toFavoriteScreen()
            bnvFavoriteBtn.setImageResource(R.drawable.ic_favorite_active)
            bnvHomeBtn.setImageResource(R.drawable.ic_home_inactive)
            bnvFavoriteIndicator.visibility = View.VISIBLE
            bnvHomeIndicator.visibility = View.INVISIBLE
        }
    }

    private fun setupObservers() = with(mBinding) {
        mVm.ldBottomNavVisibility.observe(this@MainActivity) {
            bnv.visibility = it
        }
    }

    private val navigator: Navigator = object : AppNavigator(this, R.id.container) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        mNavigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        mNavigatorHolder.removeNavigator()
        super.onPause()
    }
}