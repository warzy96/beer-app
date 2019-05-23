package com.example.beerapp.ui.beerlist;

import android.view.MenuItem;

public interface BeerListContract {

    interface View {

        void render(BeersViewModel beersViewModel);

        void showRandomBeerDialog(BeerViewModel beerViewModel, MenuItem item);
    }

    interface Presenter {

        void start();

        void setView(BeerListContract.View view);

        void onStop();

        void getRandomBeer(MenuItem item);

        void getAdditionalMovies(int page);
    }
}
