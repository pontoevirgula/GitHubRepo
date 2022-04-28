package com.chslcompany.githubrepo.core.di

import com.chslcompany.githubrepo.data.domain.ItemUseCase
import com.chslcompany.githubrepo.data.domain.ItemUseCaseImpl
import com.chslcompany.githubrepo.data.remote.api.ApiService
import com.chslcompany.githubrepo.data.repository.IKotlinRepository
import com.chslcompany.githubrepo.data.repository.KotlinRepositoriesImpl

object DependencyInjector {

    fun providerUseCase(): ItemUseCase = ItemUseCaseImpl(providerRepository())

    private fun providerRepository(): IKotlinRepository = KotlinRepositoriesImpl(providerService())

    private fun providerService() = ApiService.getService()

}