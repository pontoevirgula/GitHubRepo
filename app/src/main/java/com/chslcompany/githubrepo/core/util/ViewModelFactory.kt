package com.chslcompany.githubrepo.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chslcompany.githubrepo.repository.RepositoriesRepoImpl
import com.chslcompany.githubrepo.viewmodel.RepositoryViewModel

class ViewModelFactory(private val repositoryImpl: RepositoriesRepoImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepositoryViewModel(repositoryImpl) as T
    }
}