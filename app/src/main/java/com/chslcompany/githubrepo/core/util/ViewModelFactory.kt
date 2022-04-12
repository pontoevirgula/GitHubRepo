package com.chslcompany.githubrepo.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chslcompany.githubrepo.repository.IKotlinRepository
import com.chslcompany.githubrepo.repository.KotlinRepositoriesImpl
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel

class ViewModelFactory(private val repository: IKotlinRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(KotlinRepositoryViewModel::class.java)){
            KotlinRepositoryViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Unknown Class Name")
        }
    }
}