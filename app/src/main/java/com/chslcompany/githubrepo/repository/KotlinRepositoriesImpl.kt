package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.remote.Requester
import com.chslcompany.githubrepo.data.model.RepositoriesResponse

class KotlinRepositoriesImpl : IKotlinRepository {

    override suspend fun getKotlinRepositories(page: Int): RepositoriesResponse {
        return Requester.getService().fetchKotlinRepositories(page = page)
    }
}