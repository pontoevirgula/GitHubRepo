package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.model.RepositoriesResponse

interface IKotlinRepository {
    suspend fun getKotlinRepositories(page : Int): RepositoriesResponse
}