package com.example.beerapp.ui.searchlist;

import com.example.beerapp.ui.beerlist.BeersViewModel;

public interface SearchBeersContract {

    interface Presenter {

        void start(final String beerName, final String brewedBefore);

        void setView(SearchBeersContract.View view);

        void onStop();
    }

    interface View {

        void render(final BeersViewModel beersViewModel);
    }
}
