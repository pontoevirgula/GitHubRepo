package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.remote.Requester
import com.chslcompany.githubrepo.data.model.RepositoriesResponse

class RepositoriesRepoImpl : IRepositoryRepo{

    override suspend fun getRepositoriesRepo(page : Int): RepositoriesResponse {
        return Requester.getService().fetchKotlinRepositories(page = page)
    }
}