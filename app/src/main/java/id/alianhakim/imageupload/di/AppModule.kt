package id.alianhakim.imageupload.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.alianhakim.imageupload.api.FileApi
import id.alianhakim.imageupload.data.repository.ImageRepository
import id.alianhakim.imageupload.data.repository.ImageRepositoryRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://9ede-103-139-10-22.ap.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): FileApi {
        return retrofit.create(FileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideImageRepository(api: FileApi): ImageRepository {
        return ImageRepositoryRepositoryImpl(api)
    }
}