package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.model.RepositoriesResponse

interface IKotlinRepository {
    suspend fun getKotlinRepositories(language: String, sort: String, page: Int): RepositoriesResponse
}