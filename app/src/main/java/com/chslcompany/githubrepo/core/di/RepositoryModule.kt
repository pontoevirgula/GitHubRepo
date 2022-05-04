package com.chslcompany.githubrepo.core.di

import com.chslcompany.githubrepo.data.repository.IKotlinRepository
import com.chslcompany.githubrepo.data.repository.KotlinRepositoriesImpl
import org.koin.dsl.module

val repositoryModule = module {
        single<IKotlinRepository> { KotlinRepositoriesImpl(get()) }
}