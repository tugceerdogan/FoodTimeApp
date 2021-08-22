package com.example.kodluyoruz_yemeksepetifinalodevi.di

import androidx.viewbinding.BuildConfig
import com.example.kodluyoruz_yemeksepetifinalodevi.data.local.LocalDataSource
import com.example.kodluyoruz_yemeksepetifinalodevi.data.remote.AuthApiService
import com.example.kodluyoruz_yemeksepetifinalodevi.data.remote.AuthRemoteDataSource
import com.example.kodluyoruz_yemeksepetifinalodevi.data.remote.NetworkApiService
import com.example.kodluyoruz_yemeksepetifinalodevi.data.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(ActivityRetainedComponent ::class)
class NetworkModule {

    @Provides
    fun provideApiService(@NetworkRetrofit retrofit: Retrofit): NetworkApiService {
        return retrofit.create(NetworkApiService::class.java)
    }


    @Provides
    @NetworkRetrofit
    fun provideRetrofit(
        noAuthOkHttpClient: NoAuthOkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(noAuthOkHttpClient.okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): NoAuthOkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return provideNoAuthOkHttpClient(builder.build())
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Provides
    fun provideAuthApiService(@AuthRetrofit retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }


    @Provides
    @AuthRetrofit
    fun provideAuthRetrofit(
        authOkHttpClient: AuthOkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(authOkHttpClient.okHttpClient)
            .build()
    }


    @Provides
    fun provideAuthOkHttpClient(
        localDataSource: LocalDataSource
    ): AuthOkHttpClient {
        return provideAuthOkHttpClient(OkHttpClient.Builder()
            .addInterceptor {
                val token = localDataSource.getToken()
                val request = it.request().newBuilder().addHeader("Authorization", token!!).build()
                it.proceed(request)
            }
            .build())
    }


    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRemoteDataSource(
        networkApiService: NetworkApiService,
    ): RemoteDataSource {
        return RemoteDataSource(networkApiService)
    }

    @Provides
    fun provideAuthRemoteDataSource(
        authApiService: AuthApiService,
    ): AuthRemoteDataSource {
        return AuthRemoteDataSource(authApiService)
    }

    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://restaurant-app-android.herokuapp.com/api/")
    }

    private fun provideAuthOkHttpClient(okHttpClient: OkHttpClient): AuthOkHttpClient {
        return AuthOkHttpClient(okHttpClient)
    }

    private fun provideNoAuthOkHttpClient(noAuthOkHttpClient: OkHttpClient): NoAuthOkHttpClient {
        return NoAuthOkHttpClient(noAuthOkHttpClient)
    }
}

data class EndPoint(val url: String)

data class AuthOkHttpClient(val okHttpClient: OkHttpClient)

data class NoAuthOkHttpClient(val okHttpClient: OkHttpClient)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkRetrofit

/* @Provides
 fun provideRemoteDataSource(apiService: NetworkApiService): RemoteDataSource {
     return RemoteDataSource(apiService)
 }*/


