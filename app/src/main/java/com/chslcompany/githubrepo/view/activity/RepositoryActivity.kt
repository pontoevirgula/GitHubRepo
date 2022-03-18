package com.chslcompany.githubrepo.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chslcompany.githubrepo.R
import com.chslcompany.githubrepo.core.util.ViewModelFactory
import com.chslcompany.githubrepo.databinding.ActivityRepositoryBinding
import com.chslcompany.githubrepo.repository.RepositoriesRepoImpl
import com.chslcompany.githubrepo.view.viewmodel.RepositoryViewModel

class RepositoryActivity : AppCompatActivity() {

    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var binding: ActivityRepositoryBinding
    private var page = 1

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter(mutableListOf(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        initViewModel()
        initObservers()
        fetchData()
    }

    private fun setupViews() {
        binding = ActivityRepositoryBinding.inflate(layoutInflater)

        binding.let {
            setContentView(it.root)
            it.rvRepo.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = repositoryAdapter
            }
        }
    }

    private fun initViewModel() {
        repositoryViewModel = ViewModelProvider(
            this,
            ViewModelFactory(RepositoriesRepoImpl())
        )[RepositoryViewModel::class.java]
    }

    private fun initObservers() {
        repositoryViewModel.repositoryLiveData.observe(this) { repositoryResponse ->
            binding.pbLoading.visibility = View.GONE
            repositoryAdapter.update(repositoryResponse.items)
        }

        repositoryViewModel.viewFlipperLiveData.observe(this) {
            it?.let { viewFlipper ->
                binding.run {
                    pbLoading.visibility = View.GONE
                    viewFlipperRepository.displayedChild = viewFlipper.first
                    viewFlipper.second?.let { errorMessageId ->
                        rlError.visibility = View.VISIBLE
                        tvError.text = getString(errorMessageId)
                    }
                }
            }
        }
    }

    private fun fetchData() = repositoryViewModel.loadRepositories(page)

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}