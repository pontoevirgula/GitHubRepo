package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.model.RepositoriesResponse

interface IRepositoryRepo {
    suspend fun getRepositoriesRepo(page : Int): RepositoriesResponse
}