package com.chslcompany.githubrepo.data.repository

import com.chslcompany.githubrepo.data.remote.model.RepositoriesResponse

interface IKotlinRepository {
    suspend fun getKotlinRepositories(language: String, sort: String, page: Int): RepositoriesResponse
}