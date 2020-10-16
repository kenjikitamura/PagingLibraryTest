package jp.rainbowdevil.paginglibrarytest.repository

import jp.rainbowdevil.paginglibrarytest.api.GithubApi
import jp.rainbowdevil.paginglibrarytest.api.SearchRepositoriesResult

class GithubRepository(
        private val githubApi: GithubApi
) {
    suspend fun getSearchRepositories() : SearchRepositoriesResult {
        return githubApi.getSearchRepositories("kotlin")
    }
}