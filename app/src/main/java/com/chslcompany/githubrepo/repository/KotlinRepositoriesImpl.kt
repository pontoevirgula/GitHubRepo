package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.remote.ApiService
import com.chslcompany.githubrepo.data.model.RepositoriesResponse

class KotlinRepositoriesImpl(private val service : ApiService) : IKotlinRepository {

    override suspend fun getKotlinRepositories(page: Int): RepositoriesResponse {
        return service.getService().fetchKotlinRepositories(page = page)
    }
}