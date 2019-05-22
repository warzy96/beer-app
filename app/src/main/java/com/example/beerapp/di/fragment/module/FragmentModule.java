package com.example.beerapp.di.fragment.module;

import com.example.beerapp.di.fragment.DaggerFragment;
import com.example.beerapp.di.fragment.FragmentScope;
import com.example.beerapp.ui.beerlist.BeerListContract;
import com.example.beerapp.ui.beerlist.BeerListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public final class FragmentModule {

    private final DaggerFragment fragment;

    public FragmentModule(final DaggerFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public BeerListContract.View provideBeerListFragment() {
        return BeerListFragment.newInstance();
    }
}
