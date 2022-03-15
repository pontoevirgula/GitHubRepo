package com.chslcompany.githubrepo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chslcompany.githubrepo.R
import com.chslcompany.githubrepo.core.bases.BaseViewModel
import com.chslcompany.githubrepo.network.model.RepositoriesResponse
import com.chslcompany.githubrepo.repository.RepositoriesRepoImpl
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repositoryImpl: RepositoriesRepoImpl) : BaseViewModel() {

    val repositoryLiveData: MutableLiveData<RepositoriesResponse> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun loadRepositories() {
        viewModelScope.launch {
            try{
                val response = repositoryImpl.getRepositoriesRepo()
                if (response.items.isNullOrEmpty()){
                    viewFlipperLiveData.value =
                        Pair(VIEW_FLIPPER_ERROR, R.string.message_empty_response)
                } else {
                    repositoryLiveData.postValue(response)
                    viewFlipperLiveData.postValue(Pair(VIEW_FLIPPER_REPOSITORIES, null))
                }
            }catch (e : Exception){
                viewFlipperLiveData.value =
                    Pair(VIEW_FLIPPER_ERROR, R.string.message_error_response)
            }
        }
    }

    companion object {
        private const val VIEW_FLIPPER_REPOSITORIES = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}