package com.example.beerapp.ui.base;

import com.example.beerapp.di.fragment.DaggerFragment;

import javax.inject.Inject;

public abstract class BaseFragment<Presenter> extends DaggerFragment {

    @Inject
    public Presenter presenter;

    @Override
    public void onStop() {
        super.onStop();
    }
}
