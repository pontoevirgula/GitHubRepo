package com.chslcompany.githubrepo.data.domain

interface ItemUseCase {
    suspend fun getKotlinRepository(
        language: String,
        sort: String,
        page: Int
    ): List<ItemDomain>
}
