package com.example.beerapp.di.fragment;

import com.example.beerapp.ui.beerlist.BeerListFragment;
import com.example.beerapp.ui.beerlist.BeerListPresenter;

interface FragmentComponentInjects {


    void inject(BeerListFragment beerListFragment);

    void inject(BeerListPresenter beerListPresenter);
}
