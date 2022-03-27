package com.chslcompany.githubrepo.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chslcompany.githubrepo.core.bases.BaseActivity
import com.chslcompany.githubrepo.core.util.observeResource
import com.chslcompany.githubrepo.data.model.Item
import com.chslcompany.githubrepo.databinding.ActivityRepositoryBinding
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel

class KotlinRepositoryActivity : BaseActivity() {

    private lateinit var kotlinRepositoryViewModel: KotlinRepositoryViewModel
    private lateinit var binding: ActivityRepositoryBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var page = 1
    private var pastVisibleItems = 0
    private var totalItemCount = 0
    private var loading = false
    private var repositories = mutableListOf<Item>()

    private val kotlinRepositoryAdapter: KotlinRepositoryAdapter by lazy {
        KotlinRepositoryAdapter()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setupViews()
        kotlinRepositoryViewModel = initViewModelProvider()
        setupObservers()
        fetchData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupViews() {
        binding.let {
            setContentView(it.root)
            it.recyclerView.apply {
                setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager
                adapter = kotlinRepositoryAdapter

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

    private fun setupObservers() {
        kotlinRepositoryViewModel.kotlinRepositories.observeResource(
            this,
            onSuccess = { items ->
                if (items.isNullOrEmpty().not()) {
                    binding.pbLoading.visibility = View.GONE
                    repositories.addAll(items)
                    kotlinRepositoryAdapter.submitList(repositories)
                    loading = false
                } else {
                    //TODO lista vazia
                    binding.pbLoading.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
            },
            onError = {
                //TODO tratamento de erro
                binding.pbLoading.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            },
            onLoading = {
                binding.pbLoading.visibility = View.VISIBLE
            }
        )
    }

    private fun fetchData() = kotlinRepositoryViewModel.loadRepositories(page)


}