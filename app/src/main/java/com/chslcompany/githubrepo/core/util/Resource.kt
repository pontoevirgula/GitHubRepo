package com.chslcompany.githubrepo.core.util


sealed class Resource<T> {
    data class Success<T>(val data : T?) : Resource<T>()
    data class Error<T>(val throwable: Throwable?) : Resource<T>()
    data class Loading<T>(val isLoading : Boolean) : Resource<T>()
}
