package com.example.beerapp.router;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.beerapp.R;
import com.example.beerapp.ui.beerlist.BeerListFragment;
import com.example.beerapp.ui.searchlist.SearchBeersFragment;

import javax.inject.Inject;

public final class Router {

    @IdRes
    private static final int CONTAINER_ID = R.id.fragment_container;
    private final FragmentManager fragmentManager;

    @Inject
    public Router(final FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showBeerListScreen() {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(CONTAINER_ID, (Fragment) BeerListFragment.newInstance(), BeerListFragment.TAG)
                .commit();
    }

    public void showSearchBeerScreen() {
        fragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(CONTAINER_ID, (Fragment) SearchBeersFragment.newInstance(), SearchBeersFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    public void showRandomBeerScreen() {

    }
}
