package com.example.beerapp.ui.searchlist;

import com.example.beerapp.ui.base.BasePresenter;
import com.example.beerapp.ui.beerlist.BeerViewModelMapper;
import com.example.domain.interactor.SearchBeersUseCase;

import javax.inject.Inject;

public final class SearchBeersPresenter extends BasePresenter<SearchBeersContract.View> implements SearchBeersContract.Presenter {

    @Inject
    SearchBeersUseCase searchBeersUseCase;

    @Inject
    BeerViewModelMapper beerViewModelMapper;

    @Override
    public void start(String beerName, String brewedBefore) {
        compositeDisposable.add(searchBeersUseCase.execute(beerName, brewedBefore)
                .map(beerViewModelMapper::mapBeersViewModel)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(beersViewModel -> view.render(beersViewModel),
                        Throwable::printStackTrace));
    }

    @Override
    public void setView(SearchBeersContract.View view) {
        this.view = view;
    }
}
