package com.example.innowisepexelstestapp.presentation.view

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.app.App
import com.example.innowisepexelstestapp.databinding.ActivityMainBinding
import com.example.innowisepexelstestapp.presentation.navigation.Fragments
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import java.util.concurrent.TimeUnit

//todo удалить эти заметки нахуй
//todo закинуть библы в каталоги
//
class MainActivity : AppCompatActivity() {
    private val router: Router = App.INSTANCE.router


    private val mBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.newRootScreen(Fragments.HomeScreen)

    }
}