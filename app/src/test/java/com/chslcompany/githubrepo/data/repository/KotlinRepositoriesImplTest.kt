package com.chslcompany.githubrepo.data.repository

import com.chslcompany.githubrepo.data.remote.model.RepositoriesResponse
import com.chslcompany.githubrepo.data.remote.api.GithubApi
import com.google.common.truth.Truth
import com.google.gson.Gson
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class KotlinRepositoriesImplTest : TestCase() {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val api = mock(GithubApi::class.java)

    @Test
    fun `test response repository`() = testCoroutineDispatcher.runBlockingTest {
        val repository = KotlinRepositoriesImpl(api)
        val mockRepositoryResponse = mockResponse()

        testCoroutineDispatcher.pauseDispatcher()

        doReturn(mockRepositoryResponse).`when`(api).fetchKotlinRepositories(anyString(),
            anyString(), anyInt())

        repository.getKotlinRepositories(anyString(), anyString(), anyInt()).let {
            Truth.assertThat(it).isEqualTo(mockRepositoryResponse)
        }

    }

    private fun mockResponse(): RepositoriesResponse =
        Gson().fromJson(
            "{\n" +
                    "  \"total_count\": 798369,\n" +
                    "  \"incomplete_results\": false,\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"id\": 5152285,\n" +
                    "      \"node_id\": \"MDEwOlJlcG9zaXRvcnk1MTUyMjg1\",\n" +
                    "      \"name\": \"okhttp\",\n" +
                    "      \"full_name\": \"square/okhttp\",\n" +
                    "      \"private\": false,\n" +
                    "      \"owner\": {\n" +
                    "        \"login\": \"square\",\n" +
                    "        \"id\": 82592,\n" +
                    "        \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjgyNTky\",\n" +
                    "        \"avatar_url\": \"https://avatars.githubusercontent.com/u/82592?v=4\",\n" +
                    "        \"gravatar_id\": \"\",\n" +
                    "        \"url\": \"https://api.github.com/users/square\",\n" +
                    "        \"html_url\": \"https://github.com/square\",\n" +
                    "        \"followers_url\": \"https://api.github.com/users/square/followers\",\n" +
                    "        \"following_url\": \"https://api.github.com/users/square/following{/other_user}\",\n" +
                    "        \"gists_url\": \"https://api.github.com/users/square/gists{/gist_id}\",\n" +
                    "        \"starred_url\": \"https://api.github.com/users/square/starred{/owner}{/repo}\",\n" +
                    "        \"subscriptions_url\": \"https://api.github.com/users/square/subscriptions\",\n" +
                    "        \"organizations_url\": \"https://api.github.com/users/square/orgs\",\n" +
                    "        \"repos_url\": \"https://api.github.com/users/square/repos\",\n" +
                    "        \"events_url\": \"https://api.github.com/users/square/events{/privacy}\",\n" +
                    "        \"received_events_url\": \"https://api.github.com/users/square/received_events\",\n" +
                    "        \"type\": \"Organization\",\n" +
                    "        \"site_admin\": false\n" +
                    "      },\n" +
                    "      \"html_url\": \"https://github.com/square/okhttp\",\n" +
                    "      \"description\": \"Square’s meticulous HTTP client for the JVM, Android, and GraalVM.\",\n" +
                    "      \"fork\": false,\n" +
                    "      \"url\": \"https://api.github.com/repos/square/okhttp\",\n" +
                    "      \"forks_url\": \"https://api.github.com/repos/square/okhttp/forks\",\n" +
                    "      \"keys_url\": \"https://api.github.com/repos/square/okhttp/keys{/key_id}\",\n" +
                    "      \"collaborators_url\": \"https://api.github.com/repos/square/okhttp/collaborators{/collaborator}\",\n" +
                    "      \"teams_url\": \"https://api.github.com/repos/square/okhttp/teams\",\n" +
                    "      \"hooks_url\": \"https://api.github.com/repos/square/okhttp/hooks\",\n" +
                    "      \"issue_events_url\": \"https://api.github.com/repos/square/okhttp/issues/events{/number}\",\n" +
                    "      \"events_url\": \"https://api.github.com/repos/square/okhttp/events\",\n" +
                    "      \"assignees_url\": \"https://api.github.com/repos/square/okhttp/assignees{/user}\",\n" +
                    "      \"branches_url\": \"https://api.github.com/repos/square/okhttp/branches{/branch}\",\n" +
                    "      \"tags_url\": \"https://api.github.com/repos/square/okhttp/tags\",\n" +
                    "      \"blobs_url\": \"https://api.github.com/repos/square/okhttp/git/blobs{/sha}\",\n" +
                    "      \"git_tags_url\": \"https://api.github.com/repos/square/okhttp/git/tags{/sha}\",\n" +
                    "      \"git_refs_url\": \"https://api.github.com/repos/square/okhttp/git/refs{/sha}\",\n" +
                    "      \"trees_url\": \"https://api.github.com/repos/square/okhttp/git/trees{/sha}\",\n" +
                    "      \"statuses_url\": \"https://api.github.com/repos/square/okhttp/statuses/{sha}\",\n" +
                    "      \"languages_url\": \"https://api.github.com/repos/square/okhttp/languages\",\n" +
                    "      \"stargazers_url\": \"https://api.github.com/repos/square/okhttp/stargazers\",\n" +
                    "      \"contributors_url\": \"https://api.github.com/repos/square/okhttp/contributors\",\n" +
                    "      \"subscribers_url\": \"https://api.github.com/repos/square/okhttp/subscribers\",\n" +
                    "      \"subscription_url\": \"https://api.github.com/repos/square/okhttp/subscription\",\n" +
                    "      \"commits_url\": \"https://api.github.com/repos/square/okhttp/commits{/sha}\",\n" +
                    "      \"git_commits_url\": \"https://api.github.com/repos/square/okhttp/git/commits{/sha}\",\n" +
                    "      \"comments_url\": \"https://api.github.com/repos/square/okhttp/comments{/number}\",\n" +
                    "      \"issue_comment_url\": \"https://api.github.com/repos/square/okhttp/issues/comments{/number}\",\n" +
                    "      \"contents_url\": \"https://api.github.com/repos/square/okhttp/contents/{+path}\",\n" +
                    "      \"compare_url\": \"https://api.github.com/repos/square/okhttp/compare/{base}...{head}\",\n" +
                    "      \"merges_url\": \"https://api.github.com/repos/square/okhttp/merges\",\n" +
                    "      \"archive_url\": \"https://api.github.com/repos/square/okhttp/{archive_format}{/ref}\",\n" +
                    "      \"downloads_url\": \"https://api.github.com/repos/square/okhttp/downloads\",\n" +
                    "      \"issues_url\": \"https://api.github.com/repos/square/okhttp/issues{/number}\",\n" +
                    "      \"pulls_url\": \"https://api.github.com/repos/square/okhttp/pulls{/number}\",\n" +
                    "      \"milestones_url\": \"https://api.github.com/repos/square/okhttp/milestones{/number}\",\n" +
                    "      \"notifications_url\": \"https://api.github.com/repos/square/okhttp/notifications{?since,all,participating}\",\n" +
                    "      \"labels_url\": \"https://api.github.com/repos/square/okhttp/labels{/name}\",\n" +
                    "      \"releases_url\": \"https://api.github.com/repos/square/okhttp/releases{/id}\",\n" +
                    "      \"deployments_url\": \"https://api.github.com/repos/square/okhttp/deployments\",\n" +
                    "      \"created_at\": \"2012-07-23T13:42:55Z\",\n" +
                    "      \"updated_at\": \"2022-04-20T13:13:23Z\",\n" +
                    "      \"pushed_at\": \"2022-04-20T06:04:34Z\",\n" +
                    "      \"git_url\": \"git://github.com/square/okhttp.git\",\n" +
                    "      \"ssh_url\": \"git@github.com:square/okhttp.git\",\n" +
                    "      \"clone_url\": \"https://github.com/square/okhttp.git\",\n" +
                    "      \"svn_url\": \"https://github.com/square/okhttp\",\n" +
                    "      \"homepage\": \"https://square.github.io/okhttp/\",\n" +
                    "      \"size\": 46904,\n" +
                    "      \"stargazers_count\": 41987,\n" +
                    "      \"watchers_count\": 41987,\n" +
                    "      \"language\": \"Kotlin\",\n" +
                    "      \"has_issues\": true,\n" +
                    "      \"has_projects\": false,\n" +
                    "      \"has_downloads\": true,\n" +
                    "      \"has_wiki\": false,\n" +
                    "      \"has_pages\": true,\n" +
                    "      \"forks_count\": 8831,\n" +
                    "      \"mirror_url\": null,\n" +
                    "      \"archived\": false,\n" +
                    "      \"disabled\": false,\n" +
                    "      \"open_issues_count\": 135,\n" +
                    "      \"license\": {\n" +
                    "        \"key\": \"apache-2.0\",\n" +
                    "        \"name\": \"Apache License 2.0\",\n" +
                    "        \"spdx_id\": \"Apache-2.0\",\n" +
                    "        \"url\": \"https://api.github.com/licenses/apache-2.0\",\n" +
                    "        \"node_id\": \"MDc6TGljZW5zZTI=\"\n" +
                    "      },\n" +
                    "      \"allow_forking\": true,\n" +
                    "      \"is_template\": false,\n" +
                    "      \"topics\": [\n" +
                    "        \"android\",\n" +
                    "        \"graalvm\",\n" +
                    "        \"java\",\n" +
                    "        \"kotlin\"\n" +
                    "      ],\n" +
                    "      \"visibility\": \"public\",\n" +
                    "      \"forks\": 8831,\n" +
                    "      \"open_issues\": 135,\n" +
                    "      \"watchers\": 41987,\n" +
                    "      \"default_branch\": \"master\",\n" +
                    "      \"score\": 1.0\n" +
                    "    }\n" +
                    "]\n" +
                    "}", RepositoriesResponse::class.java
        )


}