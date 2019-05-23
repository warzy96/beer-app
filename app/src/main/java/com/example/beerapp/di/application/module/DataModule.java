package com.example.beerapp.di.application.module;

import com.example.data.BeerRepositoryImpl;
import com.example.data.network.client.BeerClient;
import com.example.data.network.config.URLs;
import com.example.data.network.mappers.BeerMapper;
import com.example.data.network.service.BeerService;
import com.example.domain.repository.BeerRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class DataModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(final OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(URLs.RETROFIT_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    BeerMapper provideBeerMapper() {
        return new BeerMapper();
    }

    @Provides
    @Singleton
    BeerService provideBeerService(final Retrofit retrofit) {
        return retrofit.create(BeerService.class);
    }

    @Provides
    @Singleton
    BeerClient provideBeerClient(final BeerService beerService, final BeerMapper beerMapper) {
        return new BeerClient(beerService, beerMapper);
    }

    @Provides
    @Singleton
    BeerRepository provideBeerRepository(final BeerClient beerClient) {
        return new BeerRepositoryImpl(beerClient);
    }
}
