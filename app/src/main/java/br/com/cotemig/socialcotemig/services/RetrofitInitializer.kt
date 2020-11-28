package br.com.cotemig.socialcotemig.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                    chain.proceed(newRequest.build())
                }
                .addInterceptor(HttpLoggingInterceptor().also { it ->
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }

    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.fluo.work/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitMockup = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://mockup.fluo.site/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun serviceAccount(): AccountService {
        return retrofit.create(AccountService::class.java)
    }

    fun serviceFeed(): FeedService {
        return retrofitMockup.create(FeedService::class.java)
    }

    fun serviceDirect(): DirectService {
        return retrofitMockup.create(DirectService::class.java)
    }

}