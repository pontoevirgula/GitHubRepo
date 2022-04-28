package com.chslcompany.githubrepo.data.domain

import com.chslcompany.githubrepo.data.remote.model.Item
import com.chslcompany.githubrepo.data.remote.model.RepositoriesResponse

object ItemMapper {

    fun convertEntityToDomain(repositoriesResponse: RepositoriesResponse) : List<ItemDomain> {
        return convertItemEntityLisToDomain(repositoriesResponse.items)
    }

    private fun convertItemEntityLisToDomain(itemEntity: List<Item>):List<ItemDomain> {
        val list = mutableListOf<ItemDomain>()
        itemEntity.forEach {
            list.add(
                ItemDomain(
                    description_domain = it.description,
                    forks_count_domain = it.forks_count,
                    full_name_domain = it.full_name,
                    html_url_domain = it.html_url,
                    name_domain = it.name,
                    stargazers_count_domain = it.stargazers_count,
                    login_domain = it.owner?.login,
                    avatar_url_domain = it.owner?.avatar_url
                )
            )
        }
        return list
    }

}