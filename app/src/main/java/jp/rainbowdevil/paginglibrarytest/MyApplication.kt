package jp.rainbowdevil.paginglibrarytest

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import jp.rainbowdevil.paginglibrarytest.api.GithubApi
import jp.rainbowdevil.paginglibrarytest.repository.FirstViewModel
import jp.rainbowdevil.paginglibrarytest.repository.GithubRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(module)
        }
    }

    // Koin Module
    private val module : Module = module {
        single {
            val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

            val httpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .addInterceptor(Interceptor { chain ->
                        val original = chain.request()
                        return@Interceptor chain.proceed(original)
                    })

            Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory((MoshiConverterFactory.create(moshi)))
                    .client(httpClient.build())
                    .build()
        }
        single {
            val retrofit = get(Retrofit::class.java)
            retrofit.create(GithubApi::class.java)
        }
        single { GithubRepository(get()) }
        factory { MainViewModel(get()) }
        factory { FirstViewModel(get()) }
    }
}