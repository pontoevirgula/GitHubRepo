package com.chslcompany.githubrepo.core.bases

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chslcompany.githubrepo.core.util.ViewModelFactory
import com.chslcompany.githubrepo.repository.KotlinRepositoriesImpl
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel

open class BaseActivity : AppCompatActivity() {

    open fun initViewModelProvider() = ViewModelProvider(
        this,
        ViewModelFactory(KotlinRepositoriesImpl())
    )[KotlinRepositoryViewModel::class.java]
}