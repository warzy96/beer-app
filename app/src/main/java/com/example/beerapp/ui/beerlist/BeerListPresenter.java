package com.example.beerapp.ui.beerlist;

import com.example.beerapp.ui.base.BasePresenter;
import com.example.domain.interactor.GetAllBeersUseCase;

import javax.inject.Inject;

public final class BeerListPresenter extends BasePresenter<BeerListContract.View> implements BeerListContract.Presenter {

    @Inject
    GetAllBeersUseCase getAllBeersUseCase;

    @Inject
    BeerViewModelMapper beerViewModelMapper;

    @Override
    public void start() {
        compositeDisposable.add(getAllBeersUseCase.execute()
                .map(beerViewModelMapper::mapBeersViewModel)
                .subscribeOn(backgroundScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(beersViewModel -> view.render(beersViewModel),
                        Throwable::printStackTrace));
    }

    @Override
    public void setView(final BeerListContract.View view) {
        this.view = view;
    }
}
