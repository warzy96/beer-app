package com.example.beerapp.ui.beerlist;

import android.view.MenuItem;

import com.example.beerapp.ui.base.BasePresenter;
import com.example.domain.interactor.GetAllBeersUseCase;
import com.example.domain.interactor.GetRandomBeerUseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public final class BeerListPresenter extends BasePresenter<BeerListContract.View> implements BeerListContract.Presenter {

    private static final int DEFAULT_PAGE = 1;

    private final List<BeerViewModel> beersCache = new ArrayList<>();

    @Inject
    GetAllBeersUseCase getAllBeersUseCase;

    @Inject
    GetRandomBeerUseCase getRandomBeerUseCase;

    @Inject
    BeerViewModelMapper beerViewModelMapper;

    @Override
    public void start() {
        compositeDisposable.add(getAllBeersUseCase.execute(DEFAULT_PAGE)
                .map(beerViewModelMapper::mapBeersViewModel)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(beersViewModel -> {
                            beersCache.clear();
                            beersCache.addAll(beersViewModel.getBeerViewModelList());
                            view.render(beersViewModel);
                        },
                        Throwable::printStackTrace));
    }

    @Override
    public void setView(final BeerListContract.View view) {
        this.view = view;
    }

    @Override
    public void getRandomBeer(final MenuItem item) {
        compositeDisposable.add(getRandomBeerUseCase.execute()
                .map(beerViewModelMapper::mapBeerViewModel)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(beersViewModel -> view.showRandomBeerDialog(beersViewModel, item),
                        Throwable::printStackTrace));
    }

    @Override
    public void getAdditionalMovies(int page) {
        compositeDisposable.add(getAllBeersUseCase.execute(page)
                .map(beerViewModelMapper::mapBeersViewModel)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(beersViewModel -> {
                            beersCache.addAll(beersViewModel.getBeerViewModelList());
                            view.render(new BeersViewModel(beersCache));
                        },
                        Throwable::printStackTrace));
    }
}
