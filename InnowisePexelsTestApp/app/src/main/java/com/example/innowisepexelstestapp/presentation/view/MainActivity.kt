package com.example.innowisepexelstestapp.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.innowisepexelstestapp.App
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.ActivityMainBinding
import com.example.innowisepexelstestapp.databinding.FragmentHomeBinding
import com.example.innowisepexelstestapp.presentation.navigation.Screens
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

//todo закинуть библы в каталоги
//todo поменять serialize в bundle на parcel
//todo покрыть юнит-тестами какую-нить view-model
//todo сделать свайпы влево и вправо на фото в списке
//todo сделать экран множественного открытия с compose
//todo extended fab
//todo перевести picasso на glide


//todo мб добавить кеширование на час
//todo мб поменять splashscreen на возможности сторонних библиотек
//todo мб добавить эффект нажатия на изображение
//todo мб добавить размер фото на кнопку скачивания



class MainActivity : AppCompatActivity() {
    private val mBinding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var mNavigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.homeFragment())))
        }

        showSplashScreenAnim()
        delayedHideSplashScreen()
    }

    @SuppressLint("CheckResult")
    private fun delayedHideSplashScreen() = with(mBinding) {
        Observable.timer(1200, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                logo.visibility = View.GONE
                logoBackground.visibility = View.GONE
            }
    }

    private fun showSplashScreenAnim() {
        val animation = AnimationUtils.loadAnimation(baseContext, R.anim.fall_animation)
        mBinding.logo.startAnimation(animation)
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