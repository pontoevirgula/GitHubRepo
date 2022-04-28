package com.chslcompany.githubrepo.data.repository

import com.chslcompany.githubrepo.data.remote.model.RepositoriesResponse
import com.chslcompany.githubrepo.data.remote.api.GithubApi

class KotlinRepositoriesImpl(private val service : GithubApi) : IKotlinRepository {

    override suspend fun getKotlinRepositories(language: String, sort: String, page: Int): RepositoriesResponse {
        return service.fetchKotlinRepositories(page = page)
    }
}