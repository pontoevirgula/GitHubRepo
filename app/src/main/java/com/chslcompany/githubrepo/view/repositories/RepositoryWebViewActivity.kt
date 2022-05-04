package com.chslcompany.githubrepo.view.repositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chslcompany.githubrepo.databinding.ActivityRepositoryWebViewBinding

class RepositoryWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepositoryWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openRepository()
    }

    private fun openRepository() {
        binding.run {
            webView.loadUrl(getUrl())
        }
    }

    private fun getUrl() = intent?.getStringExtra(KotlinRepositoryActivity.URL) ?: ""

}