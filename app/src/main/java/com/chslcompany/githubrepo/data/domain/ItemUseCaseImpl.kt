package com.chslcompany.githubrepo.data.domain

import com.chslcompany.githubrepo.data.repository.IKotlinRepository

class ItemUseCaseImpl(private val repository: IKotlinRepository) : ItemUseCase {

    override suspend fun getKotlinRepository(
        language: String,
        sort: String,
        page: Int
    ): List<ItemDomain> {
        val callApi = repository.getKotlinRepositories(language, sort, page)
        return ItemMapper.convertEntityToDomain(callApi)
    }
}