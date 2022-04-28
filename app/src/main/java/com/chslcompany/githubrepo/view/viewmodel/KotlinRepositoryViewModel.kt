package com.chslcompany.githubrepo.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chslcompany.githubrepo.core.bases.BaseViewModel
import com.chslcompany.githubrepo.core.util.Resource
import com.chslcompany.githubrepo.data.domain.ItemDomain
import com.chslcompany.githubrepo.data.domain.ItemUseCase
import kotlinx.coroutines.launch

class KotlinRepositoryViewModel(private val itemUseCase: ItemUseCase) : BaseViewModel() {

    private val _kotlinRepositoriesLiveData = MutableLiveData<Resource<List<ItemDomain>>>()
    val kotlinRepositoriesLiveData : LiveData<Resource<List<ItemDomain>>> = _kotlinRepositoriesLiveData

    fun loadRepositories(language: String = "language:kotlin", sort: String = "stars", page: Int) {
        viewModelScope.launch {
            with(_kotlinRepositoriesLiveData) {
                loading(true)
                try {
                    val responses = itemUseCase.getKotlinRepository(language, sort, page)
                    success(responses)
                } catch (e: Exception) {
                    error(e)
                }
            }
        }
    }

}