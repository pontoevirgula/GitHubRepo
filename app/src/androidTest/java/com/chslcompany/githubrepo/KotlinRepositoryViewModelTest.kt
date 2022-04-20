package com.chslcompany.githubrepo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KotlinRepositoryViewModelTest : TestCase(){

    private lateinit var viewModel: KotlinRepositoryViewModel

    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun testAddRepositories(){
    }
}