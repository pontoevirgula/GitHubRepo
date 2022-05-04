package com.chslcompany.githubrepo.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chslcompany.githubrepo.data.domain.ItemUseCase
import com.chslcompany.githubrepo.view.repositories.KotlinRepositoryViewModel

class ViewModelFactory(private val useCase: ItemUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(KotlinRepositoryViewModel::class.java)){
            KotlinRepositoryViewModel(useCase) as T
        }else{
            throw IllegalArgumentException("Unknown Class Name")
        }
    }
}