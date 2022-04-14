package com.chslcompany.githubrepo.core.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<Resource<T>>.observeResource(
    owner: LifecycleOwner,
    onSuccess: (T) -> Unit,
    onError : (Throwable) -> Unit,
    onLoading : ((Boolean) -> Unit)? = null
){
    observe(owner, Observer { resource->
        when(resource) {
            is Resource.Success -> resource.data?.let { onSuccess.invoke(it) }
            is Resource.Error -> resource.throwable?.let { onError.invoke(it) }
            is Resource.Loading -> onLoading?.invoke(resource.isLoading)
        }
    })
}

fun View.isVisibleOrGone(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}
