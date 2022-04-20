package com.chslcompany.githubrepo.data.domain

import android.os.Parcelable
import com.chslcompany.githubrepo.data.model.Item
import com.chslcompany.githubrepo.data.model.Owner
import com.chslcompany.githubrepo.data.model.RepositoriesResponse
import kotlinx.parcelize.Parcelize


@Parcelize
data class KotlinRepositoryDomain(
    val itemDomain : List<ItemDomain> = mutableListOf()
): Parcelable{
    companion object{
        fun convertEntityToDomain(repositoriesResponse: RepositoriesResponse) : KotlinRepositoryDomain{
            return KotlinRepositoryDomain(convertItemEntityLisToDomain(repositoriesResponse.items))
        }

        private fun convertItemEntityLisToDomain(itemEntity: List<Item>): List<ItemDomain> {
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
                        owner_domain = convertOwnerEntityToDomain(it.owner)
                    )
                )
            }
            return list
        }

        private fun convertOwnerEntityToDomain(ownerEntity: Owner?) : OwnerDomain? {
           ownerEntity?.let {
               return OwnerDomain(
                   login_domain = ownerEntity.login,
                   avatar_url_domain = ownerEntity.avatar_url
               )
           } ?: kotlin.run {
               return null
           }
        }
    }
}

@Parcelize
data class ItemDomain (
    var description_domain: String? = "",
    var forks_count_domain: Int = 0,
    var full_name_domain: String? = "",
    var html_url_domain: String? = "",
    var name_domain: String? = "",
    var owner_domain: OwnerDomain? = null,
    var stargazers_count_domain: Int = 0,
): Parcelable

@Parcelize
data class OwnerDomain(
    var login_domain : String? = "",
    var avatar_url_domain : String? = ""
): Parcelable