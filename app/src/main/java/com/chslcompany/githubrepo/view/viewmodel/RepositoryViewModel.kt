package com.chslcompany.githubrepo.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chslcompany.githubrepo.data.model.RepositoriesResponse
import com.chslcompany.githubrepo.repository.RepositoriesRepoImpl
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repositoryImpl: RepositoriesRepoImpl) : ViewModel() {

    val repositoryLiveData: MutableLiveData<RepositoriesResponse> = MutableLiveData()

    fun loadRepositories(page: Int) {
        viewModelScope.launch {
            try {
                val response = repositoryImpl.getRepositoriesRepo(page)
                if (response.items.isNullOrEmpty()) {
                    //TODO tratamento de lista vazia
                } else {
                    repositoryLiveData.value = response
                }
            } catch (e: Exception) {
                //TODO tratamento de erro
            }
        }
    }

}