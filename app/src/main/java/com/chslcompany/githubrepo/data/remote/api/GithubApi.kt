package com.chslcompany.githubrepo.data.remote.api

import com.chslcompany.githubrepo.data.remote.model.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    suspend fun fetchKotlinRepositories(
        @Query("q") language: String = "kotlin",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): RepositoriesResponse

}