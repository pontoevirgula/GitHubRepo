package com.chslcompany.githubrepo.core.bases

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.chslcompany.githubrepo.core.util.ViewModelFactory
import com.chslcompany.githubrepo.repository.RepositoriesRepoImpl
import com.chslcompany.githubrepo.view.viewmodel.RepositoryViewModel

open class BaseActivity : AppCompatActivity() {

    open fun initViewModelProvider() = ViewModelProvider(
        this,
        ViewModelFactory(RepositoriesRepoImpl())
    )[RepositoryViewModel::class.java]
}