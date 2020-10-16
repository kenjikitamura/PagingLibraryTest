package jp.rainbowdevil.paginglibrarytest

import android.util.Log
import androidx.lifecycle.ViewModel
import jp.rainbowdevil.paginglibrarytest.repository.GithubRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext

class MainViewModel(
        val githubRepository: GithubRepository
) : ViewModel() {
    fun test() {
        Log.d("test", "TEST!!----------------------------------------------------------------------")
        GlobalScope.launch {
            val result = githubRepository.getSearchRepositories()
            Log.d("test", "result = $result")
        }
    }
}