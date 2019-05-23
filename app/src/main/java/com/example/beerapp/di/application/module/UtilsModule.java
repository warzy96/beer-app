package com.example.beerapp.di.application.module;

import android.content.Context;

import com.example.beerapp.di.application.ForApplication;
import com.example.beerapp.utils.ImageLoader;
import com.example.beerapp.utils.ImageLoaderImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UtilsModule {

    @Provides
    @Singleton
    public ImageLoader provideImageLoader(@ForApplication final Context context) {
        return new ImageLoaderImpl(context);
    }
}
