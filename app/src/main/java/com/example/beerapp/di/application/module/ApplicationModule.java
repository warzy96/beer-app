package com.example.beerapp.di.application.module;

import android.content.Context;

import com.example.beerapp.app.BeerApplication;
import com.example.beerapp.di.application.ForApplication;
import com.example.beerapp.ui.beerlist.BeerViewModelMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final BeerApplication beerApplication;

    public ApplicationModule(BeerApplication beerApplication) {
        this.beerApplication = beerApplication;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideContext() {
        return beerApplication;
    }

    @Provides
    @Singleton
    BeerApplication provideBeerApplication() {
        return beerApplication;
    }

    @Provides
    @Singleton
    BeerViewModelMapper provideBeerViewModelMapper() {
        return new BeerViewModelMapper();
    }
}
