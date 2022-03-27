package com.chslcompany.githubrepo.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chslcompany.githubrepo.repository.KotlinRepositoriesImpl
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel

class ViewModelFactory(private val repositoryImpl: KotlinRepositoriesImpl) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(KotlinRepositoryViewModel::class.java)){
            KotlinRepositoryViewModel(repositoryImpl) as T
        }else{
            throw IllegalArgumentException("Unknown Class Name")
        }
    }
}