package com.chslcompany.githubrepo.core.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chslcompany.githubrepo.core.util.NetworkChangeReceiver
import com.chslcompany.githubrepo.core.util.NetworkChangeReceiver.Companion.networkChangeReceiver
import com.chslcompany.githubrepo.core.util.ViewModelFactory
import com.chslcompany.githubrepo.repository.KotlinRepositoriesImpl
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel

abstract class BaseActivity : AppCompatActivity(), NetworkChangeReceiver.ConnectionChangeCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkChangeReceiver(this)
    }

    open fun initViewModelProvider() = ViewModelProvider(
        this,
        ViewModelFactory(KotlinRepositoriesImpl())
    )[KotlinRepositoryViewModel::class.java]


}