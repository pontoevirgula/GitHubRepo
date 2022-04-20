package com.chslcompany.githubrepo.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chslcompany.githubrepo.core.bases.BaseViewModel
import com.chslcompany.githubrepo.core.util.Resource
import com.chslcompany.githubrepo.data.model.Item
import com.chslcompany.githubrepo.repository.IKotlinRepository
import kotlinx.coroutines.launch

class KotlinRepositoryViewModel(private val repository: IKotlinRepository) : BaseViewModel() {

    private val _kotlinRepositoriesLiveData = MutableLiveData<Resource<List<Item>>>()
    val kotlinRepositoriesLiveData : LiveData<Resource<List<Item>>> = _kotlinRepositoriesLiveData

    fun loadRepositories(page: Int) {
        viewModelScope.launch {
            with(_kotlinRepositoriesLiveData) {
                loading(true)
                try {
                    val responses = repository.getKotlinRepositories(page).items
                    success(responses)
                } catch (e: Exception) {
                    error(e)
                }
            }
        }
    }

}