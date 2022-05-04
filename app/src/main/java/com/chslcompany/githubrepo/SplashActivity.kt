package com.chslcompany.githubrepo

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.chslcompany.githubrepo.core.di.*
import com.chslcompany.githubrepo.databinding.ActivitySplashBinding
import com.chslcompany.githubrepo.view.repositories.KotlinRepositoryActivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            initApp()
        }, 2000)
    }

    private fun initApp() {
        initKoinApplication(application)
        startActivity(Intent(this, KotlinRepositoryActivity::class.java))
        finish()
    }

    private fun initKoinApplication(application: Application) {
        startKoin {
            androidContext(application.applicationContext)
            modules(
                listOf(
                    apiModule,
                    networkModule,
                    useCaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}