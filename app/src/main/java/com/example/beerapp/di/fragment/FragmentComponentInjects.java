package com.example.beerapp.di.fragment;

import com.example.beerapp.ui.beerlist.BeerListFragment;
import com.example.beerapp.ui.beerlist.BeerListPresenter;
import com.example.beerapp.ui.searchlist.SearchBeersFragment;
import com.example.beerapp.ui.searchlist.SearchBeersPresenter;

interface FragmentComponentInjects {


    void inject(BeerListFragment beerListFragment);

    void inject(BeerListPresenter beerListPresenter);

    void inject(SearchBeersFragment searchBeersFragment);

    void inject(SearchBeersPresenter searchBeersPresenter);
}
