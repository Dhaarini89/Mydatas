package com.android.example.cutyoutubefree.network

import com.android.example.freeblacklist.network.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


private const val BASE_URL="http://192.168.1.254/"
//private const val BASE_URL="https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val okHttpClient = OkHttpClient.Builder()
    .readTimeout(60, TimeUnit.SECONDS)
    .connectTimeout(60, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface FreeApiService
{
    @Headers("Content-Type: application/json")
    @POST("api/v8/login/authorize/")
    suspend fun postFreeInfo(@Body freepost : FreePost) : FreeResponse

    @GET("api/v8/login/authorize/{track_id}")
    suspend fun getFreeStatus(@Path("track_id") track_id :String) : FreeStatus

    @POST("api/v8/wifi/mac_filter/")
    suspend fun postFreeBlackList(@HeaderMap headers : Map<String,String>, @Body freePostBlackList: FreePostBlackList ) : FreePostBlackListResponse

    @DELETE("api/v8/wifi/mac_filter/{mac_addr}-blacklist")
    suspend fun removeBlackListFilter(@HeaderMap headers : Map<String,String>, @Path("mac_addr") mac_addr : String) : FreeRemoveBlackListResponse


    @GET("api/v8/login")
    suspend fun getFreeChallenge() : FreeChallenge

    @Headers("Content-Type: application/json")
    @POST("api/v8/login/session/")
    suspend fun postFreeSession(@Body freesession : FreeSession) : FreeSessionToken


}

object FreeApi
{
    val retrofitService : FreeApiService by lazy() {
            retrofit.create(FreeApiService::class.java)

        }
}