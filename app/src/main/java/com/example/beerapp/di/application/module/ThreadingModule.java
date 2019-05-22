package com.example.beerapp.di.application.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Module
public final class ThreadingModule {

    public static final String BACKGROUND_SCHEDULER = "background_scheduler";
    public static final String MAIN_SCHEDULER = "main_scheduler";

    @Provides
    @Singleton
    @Named(BACKGROUND_SCHEDULER)
    Scheduler provideBackgroundScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(MAIN_SCHEDULER)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
