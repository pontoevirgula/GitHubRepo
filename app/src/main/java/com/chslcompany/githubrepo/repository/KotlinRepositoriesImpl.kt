package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.model.RepositoriesResponse
import com.chslcompany.githubrepo.data.remote.GithubApi

class KotlinRepositoriesImpl(private val service : GithubApi) : IKotlinRepository {

    override suspend fun getKotlinRepositories(language: String, sort: String, page: Int): RepositoriesResponse {
        return service.fetchKotlinRepositories(page = page)
    }
}