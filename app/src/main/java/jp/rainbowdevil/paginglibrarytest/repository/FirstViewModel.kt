package jp.rainbowdevil.paginglibrarytest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*

class FirstViewModel(
) : ViewModel() {

    private val factory :DataSource.Factory<Int, String> = TestDataSourceFactory()
    val list: LiveData<PagedList<String>> = factory.toLiveData(50, 0)
}

class TestDataSource() : PageKeyedDataSource<Int, String>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, String>
    ) {
        callback.onResult(createDate(0), null, 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        callback.onResult(createDate(params.key), params.key + 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, String>) {
        callback.onResult(createDate(params.key), params.key + 1)
    }

    fun createDate(page: Int): List<String> {
        val list = mutableListOf<String>()
        for (i in 0..10) {
            list.add("$page data ($i)")
        }
        return list
    }
}

class TestDataSourceFactory() : DataSource.Factory<Int, String>() {
    override fun create(): DataSource<Int, String> {
        return TestDataSource()
    }
}