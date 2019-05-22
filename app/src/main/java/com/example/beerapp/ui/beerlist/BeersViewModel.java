package com.example.beerapp.ui.beerlist;

import java.util.List;

public final class BeersViewModel {

    private List<BeerViewModel> beerViewModelList;

    public BeersViewModel(List<BeerViewModel> beerViewModelList) {
        this.beerViewModelList = beerViewModelList;
    }
}
