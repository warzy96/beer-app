package com.example.beerapp.ui.beerlist;

import com.example.domain.model.Beer;

import java.util.LinkedList;
import java.util.List;

public final class BeerViewModelMapper {

    public BeersViewModel mapBeersViewModel(List<Beer> beerList) {
        final LinkedList<BeerViewModel> beerViewModels = new LinkedList<>();
        for (final Beer beer : beerList) {
            beerViewModels.add(new BeerViewModel(beer));
        }
        return new BeersViewModel(beerViewModels);

    }
}
