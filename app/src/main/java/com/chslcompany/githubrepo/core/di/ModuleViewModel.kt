package com.chslcompany.githubrepo.core.di

import com.chslcompany.githubrepo.view.repositories.KotlinRepositoryViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
        KotlinRepositoryViewModel(get())
    }
}