package com.chslcompany.githubrepo.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chslcompany.githubrepo.core.di.DependencyInjector
import com.chslcompany.githubrepo.core.util.ViewModelFactory
import com.chslcompany.githubrepo.core.util.observeResource
import com.chslcompany.githubrepo.data.model.Item
import com.chslcompany.githubrepo.databinding.ActivityRepositoryBinding
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel

class KotlinRepositoryActivity : AppCompatActivity() {

    private lateinit var kotlinRepositoryViewModel: KotlinRepositoryViewModel
    private lateinit var binding: ActivityRepositoryBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var page = 1
    private var pastVisibleItems = 0
    private var totalItemCount = 0
    private var loading = false
    private var repositories = mutableListOf<Item>()

    private val kotlinRepositoryAdapter: KotlinRepositoryAdapter by lazy {
        KotlinRepositoryAdapter(mutableListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setupViews()
        kotlinRepositoryViewModel = initViewModelProvider()
        setupObservers()
        fetchData()
    }

    private fun setupViews() {
        binding.let {
            setContentView(it.root)
            it.recyclerView.apply {
                setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(context)
                layoutManager = linearLayoutManager
                adapter = kotlinRepositoryAdapter

                setupScrollListener()
            }
        }
    }

    private fun setupObservers() {
        kotlinRepositoryViewModel.kotlinRepositoriesLiveData.observeResource(
            this,
            onSuccess = { items ->
                binding.includeError.rlError.visibility = View.GONE
                binding.includeLoading.loadingShimmer.visibility = View.GONE

                if (items.isNullOrEmpty().not()) {
                    binding.includeLoading.loadingShimmer.visibility = View.GONE
                    repositories.addAll(items)
                    kotlinRepositoryAdapter.submitList(repositories)
                    loading = false
                    binding.includeEmptyList.rlEmptyList.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                } else {
                    binding.includeEmptyList.rlEmptyList.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            },
            onError = {
                binding.includeLoading.loadingShimmer.visibility = View.GONE
                binding.includeEmptyList.rlEmptyList.visibility = View.GONE
                binding.includeError.rlError.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.includeError.tvError.setOnClickListener { fetchData() }
            },
            onLoading = {
                binding.includeLoading.loadingShimmer.visibility = View.VISIBLE
                binding.includeEmptyList.rlEmptyList.visibility = View.GONE
                binding.includeError.rlError.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            }
        )
    }

    private fun fetchData() = kotlinRepositoryViewModel.loadRepositories(page)

    private fun RecyclerView.setupScrollListener() {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    totalItemCount = linearLayoutManager.itemCount
                    pastVisibleItems = linearLayoutManager.findLastVisibleItemPosition()

                    if (loading.not() && pastVisibleItems >= (totalItemCount - 1)) {
                        loading = true
                        binding.includeLoading.loadingShimmer.visibility = View.VISIBLE
                        page++
                        fetchData()
                    }
                }
            }
        })
    }

    private fun initViewModelProvider() = ViewModelProvider(
        this,
        ViewModelFactory(DependencyInjector.providerRepository())
    )[KotlinRepositoryViewModel::class.java]

    override fun onResume() {
        super.onResume()
        loading = false
    }

}