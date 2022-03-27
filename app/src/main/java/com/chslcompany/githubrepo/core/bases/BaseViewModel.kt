package com.chslcompany.githubrepo.core.bases

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chslcompany.githubrepo.core.util.Resource

abstract class BaseViewModel : ViewModel() {

    protected fun <T> MutableLiveData<Resource<T>>.success(data : T){
        postValue(Resource.Success(data))
    }

    protected fun <T> MutableLiveData<Resource<T>>.error(throwable: Throwable?){
        value = Resource.Error(throwable)
    }

    protected fun <T> MutableLiveData<Resource<T>>.loading(isLoading: Boolean){
        value = Resource.Loading(isLoading)
    }
}