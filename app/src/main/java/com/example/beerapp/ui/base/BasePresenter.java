package com.example.beerapp.ui.base;

import com.example.beerapp.di.application.module.ThreadingModule;
import com.example.beerapp.router.Router;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<View> {

    @Inject
    protected Router router;

    @Inject
    @Named(ThreadingModule.BACKGROUND_SCHEDULER)
    public Scheduler backgroundScheduler;

    @Inject
    @Named(ThreadingModule.MAIN_SCHEDULER)
    public Scheduler mainThreadScheduler;

    @Inject
    protected CompositeDisposable compositeDisposable;

    public View view;

    public void onStop() {
        compositeDisposable.clear();
    }
}
