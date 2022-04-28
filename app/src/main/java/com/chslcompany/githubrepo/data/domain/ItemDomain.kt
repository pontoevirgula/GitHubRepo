package com.chslcompany.githubrepo.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemDomain (
    var description_domain: String? = "",
    var forks_count_domain: Int = 0,
    var full_name_domain: String? = "",
    var html_url_domain: String? = "",
    var name_domain: String? = "",
    var login_domain: String? = "",
    var avatar_url_domain: String? = "",
    var stargazers_count_domain: Int = 0,
): Parcelable
