package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.network.data.Requester
import com.chslcompany.githubrepo.network.model.RepositoriesResponse

class RepositoriesRepoImpl : IRepositoryRepo{

    override suspend fun getRepositoriesRepo(): RepositoriesResponse {
        return Requester.getService().fetchKotlinRepositories()
    }
}