package com.chslcompany.githubrepo.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chslcompany.githubrepo.core.bases.BaseActivity
import com.chslcompany.githubrepo.data.model.Item
import com.chslcompany.githubrepo.databinding.ActivityRepositoryBinding
import com.chslcompany.githubrepo.view.viewmodel.RepositoryViewModel

class RepositoryActivity : BaseActivity() {

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var binding: ActivityRepositoryBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var page = 1
    private var pastVisibleItems = 0
    private var totalItemCount = 0
    private var loading = false
    private var repositories = mutableListOf<Item>()

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setupViews()
        repositoryViewModel = initViewModelProvider()
        setupObservers()
        fetchData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupViews() {
        binding.let {
            setContentView(it.root)
            it.rvRepo.apply {
                setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager
                adapter = repositoryAdapter

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0) {
                            totalItemCount = linearLayoutManager.itemCount
                            pastVisibleItems = linearLayoutManager.findLastVisibleItemPosition()

                            if (loading.not() && pastVisibleItems >= totalItemCount - 1) {
                                    loading = true
                                    binding.pbLoading.visibility = View.VISIBLE
                                    page++
                                    fetchData()
                            }
                        }
                    }
                })
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupObservers() {
        repositoryViewModel.repositoryLiveData.observe(this) { repositoryResponse ->
            binding.pbLoading.visibility = View.GONE
            repositories.addAll(repositoryResponse.items)
            repositoryAdapter.submitList(repositories)
            loading = false
        }

        //TODO tratamento de erro
    }

    private fun fetchData() = repositoryViewModel.loadRepositories(page)


}