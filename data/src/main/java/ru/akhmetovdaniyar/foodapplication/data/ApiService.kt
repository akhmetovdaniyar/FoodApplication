package ru.akhmetovdaniyar.foodapplication.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.akhmetovdaniyar.foodapplication.data.category.Menu
import ru.akhmetovdaniyar.foodapplication.data.dishes.Dishes
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategories(): Menu

    @GET("v3/c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): Dishes

    companion object {
        var apiService: ApiService? = null
        private const val mainUrl = "https://run.mocky.io/"

        fun getInstance(): ApiService {
            val httpClient = OkHttpClient.Builder()
                .callTimeout(1, TimeUnit.MINUTES)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(mainUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}