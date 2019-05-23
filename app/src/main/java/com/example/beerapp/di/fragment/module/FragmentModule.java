package com.example.beerapp.di.fragment.module;

import android.view.LayoutInflater;

import com.example.beerapp.di.fragment.DaggerFragment;
import com.example.beerapp.di.fragment.FragmentScope;
import com.example.beerapp.ui.beerlist.BeerListAdapter;
import com.example.beerapp.ui.beerlist.BeerListContract;
import com.example.beerapp.ui.beerlist.BeerListFragment;
import com.example.beerapp.utils.ImageLoader;

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

    @Provides
    @FragmentScope
    public BeerListAdapter provideBeerListAdapter(final LayoutInflater layoutInflater, final ImageLoader imageLoader) {
        return new BeerListAdapter(layoutInflater, imageLoader);
    }
}
