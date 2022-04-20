package com.chslcompany.githubrepo.repository

import com.chslcompany.githubrepo.data.model.RepositoriesResponse

class MockRepository : IKotlinRepository  {
    override suspend fun getKotlinRepositories(page: Int) = RepositoriesResponse(
        false,
        emptyList(),
        0
    )
}