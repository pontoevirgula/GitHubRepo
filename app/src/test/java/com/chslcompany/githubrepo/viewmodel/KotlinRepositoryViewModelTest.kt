package com.chslcompany.githubrepo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chslcompany.githubrepo.core.util.Resource
import com.chslcompany.githubrepo.data.domain.ItemDomain
import com.chslcompany.githubrepo.data.domain.ItemUseCaseImpl
import com.chslcompany.githubrepo.view.viewmodel.KotlinRepositoryViewModel
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.exceptions.base.MockitoException
import java.io.IOException

@ExperimentalCoroutinesApi
class KotlinRepositoryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: KotlinRepositoryViewModel
    private val mockItemUseCaseImpl = mock(ItemUseCaseImpl::class.java)

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = KotlinRepositoryViewModel(mockItemUseCaseImpl)
    }

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `test get repository success`() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()

        doReturn(mock()).`when`(mockItemUseCaseImpl).getKotlinRepository(
            anyString(),
            anyString(),
            anyInt()
        )
        viewModel.loadRepositories(anyString(), anyString(), anyInt())

        testCoroutineDispatcher.resumeDispatcher()

        Truth.assertThat(viewModel.kotlinRepositoriesLiveData.value)
            .isEqualTo(Resource.Success(mock()))
    }

    @Test (expected = MockitoException::class)
    fun `test get repository error`() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()

        doThrow(IOException::class.java).`when`(mockItemUseCaseImpl).getKotlinRepository(
            anyString(),
            anyString(),
            anyInt()
        )

        viewModel.loadRepositories(anyString(), anyString(), anyInt())

        testCoroutineDispatcher.resumeDispatcher()

        Truth.assertThat(viewModel.kotlinRepositoriesLiveData.value)
            .isEqualTo(Resource.Error<ItemDomain>(IOException()))
    }


    private fun mock() = mutableListOf(
        ItemDomain(
            description_domain = "Square’ s meticulous HTTP client for the JVM,Android and GraalVM .",
            forks_count_domain = 8840,
            full_name_domain = "square / okhttp",
            html_url_domain = "https://github.com/square/okhttp",
            name_domain = "okhttp",
            login_domain = "square",
            avatar_url_domain = "https://avatars.githubusercontent.com/u/82592?v=4",
            stargazers_count_domain = 42027
        )
    )
}