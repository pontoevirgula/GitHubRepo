package com.chslcompany.githubrepo.core.di

import com.chslcompany.githubrepo.data.remote.ApiService
import com.chslcompany.githubrepo.repository.IKotlinRepository
import com.chslcompany.githubrepo.repository.KotlinRepositoriesImpl

object DependencyInjector {

    fun providerRepository(): IKotlinRepository = KotlinRepositoriesImpl(providerService())

    private fun providerService() = ApiService.getService()

    //private fun provideMockRepository() : IKotlinRepository =

}