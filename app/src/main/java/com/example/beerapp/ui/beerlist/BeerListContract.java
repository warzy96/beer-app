package com.example.beerapp.ui.beerlist;

public interface BeerListContract {

    interface View {

        void render(BeersViewModel beersViewModel);
    }

    interface Presenter {

        void start();

        void setView(BeerListContract.View view);

        void onStop();
    }
}
