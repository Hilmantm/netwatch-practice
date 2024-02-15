package id.motionlab.netwatch.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.motionlab.netwatch.data.MovieRepositoryImpl
import id.motionlab.netwatch.data.sources.network.NetworkDataSource
import id.motionlab.netwatch.modules.movie.interactor.MovieInteractor
import id.motionlab.netwatch.modules.movie.repository.MovieRepository
import id.motionlab.netwatch.modules.movie.usecase.MovieUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiConfig {

    @Provides
    @Singleton
    fun providesChuckerCollector(
        @ApplicationContext ctx: Context
    ): ChuckerCollector {
        return ChuckerCollector(
            context = ctx,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_DAY
        )
    }

    @Provides
    @Singleton
    fun providesChuckerInterceptor(
        @ApplicationContext ctx: Context,
        collector: ChuckerCollector
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context = ctx)
            .collector(collector = collector)
            .maxContentLength(length = 250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(
                enable = true
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        @ApplicationContext ctx: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(chuckerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesNetworkService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): NetworkDataSource {
        return retrofit.create(NetworkDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        networkDataSource: NetworkDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(networkDataSource)
    }

    @Provides
    @Singleton
    fun provideMovieInteractor(
        movieRepository: MovieRepository
    ): MovieUseCase {
        return MovieInteractor(movieRepository)
    }
}