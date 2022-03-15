package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.network.model.RepositoriesResponse
import retrofit2.Response

interface IRepositoryRepo {
    suspend fun getRepositoriesRepo(): RepositoriesResponse
}