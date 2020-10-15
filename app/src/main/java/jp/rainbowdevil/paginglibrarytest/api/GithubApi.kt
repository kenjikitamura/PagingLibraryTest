package jp.rainbowdevil.paginglibrarytest.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    /**
     * https://docs.github.com/en/free-pro-team@latest/rest/reference/search#search-repositories
     */
    @GET("/search/repositories")
    fun getSearchRepositories(
            @Query("q") query: String
    ) : Call<SearchRepositoriesResult>
}

class SearchRepositoriesResult (
    val totalCount: Int,
    val incompleteResults: Boolean
)