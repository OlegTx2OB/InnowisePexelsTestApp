package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.app.App
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
import javax.inject.Inject

//todo удалить эти заметки нахуй
//todo закинуть библы в каталоги

class MainActivity : AppCompatActivity() {
    private val mBinding by viewBinding(ActivityMainBinding::bind)
    private val mVm: MainViewModel by injectViewModel()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.splashScreenFragment())))
        }
        setupClickListeners()
    }

    private fun setupClickListeners() = with(mBinding) {
        bnvHome.setOnClickListener {

        }
        bnvFavorite.setOnClickListener {

        }
    }

    private val navigator: Navigator = object : AppNavigator(this, R.id.container) {
        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }
}