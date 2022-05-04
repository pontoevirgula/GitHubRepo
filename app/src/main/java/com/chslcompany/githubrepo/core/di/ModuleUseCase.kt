package com.chslcompany.githubrepo.core.di

import com.chslcompany.githubrepo.data.domain.ItemUseCase
import com.chslcompany.githubrepo.data.domain.ItemUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
        single<ItemUseCase> { ItemUseCaseImpl(get()) }
}
