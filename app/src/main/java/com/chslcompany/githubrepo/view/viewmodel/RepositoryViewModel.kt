package com.chslcompany.githubrepo.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chslcompany.githubrepo.core.bases.BaseViewModel
import com.chslcompany.githubrepo.core.util.Resource
import com.chslcompany.githubrepo.data.model.Item
import com.chslcompany.githubrepo.repository.RepositoriesRepoImpl
import kotlinx.coroutines.launch

class RepositoryViewModel(private val repositoryImpl: RepositoriesRepoImpl) : BaseViewModel() {

    val kotlinRepositories = MutableLiveData<Resource<List<Item>>>()

    fun loadRepositories(page: Int) {
        viewModelScope.launch {
            with(kotlinRepositories) {
                loading(true)
                try {
                    val response = repositoryImpl.getRepositoriesRepo(page)
                    success(response.items)
                } catch (e: Exception) {
                    error(e)
                }finally {
                    loading(false)
                }
            }
        }
    }

}