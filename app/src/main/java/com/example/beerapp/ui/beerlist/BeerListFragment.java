package com.example.beerapp.ui.beerlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beerapp.R;
import com.example.beerapp.di.fragment.FragmentComponent;
import com.example.beerapp.ui.base.BaseFragment;
import com.example.beerapp.utils.ImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.plugins.RxJavaPlugins;

public final class BeerListFragment extends BaseFragment<BeerListContract.Presenter> implements BeerListContract.View {

    public static final String TAG = "BeerListFragment";

    private static final int DEFAULT_PAGE = 1;

    private int page = DEFAULT_PAGE;

    @Inject
    BeerListAdapter beerListAdapter;

    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.beer_list_recycler_view)
    RecyclerView beerListRecyclerView;

    @BindView(R.id.no_results_text_view)
    TextView noResultsTextView;

    @LayoutRes
    private static final int BEER_LIST_FRAGMENT = R.layout.fragment_beer_list;

    @LayoutRes
    private final static int POPUP_LAYOUT = R.layout.beer_details_popup_layout;


    public static BeerListContract.View newInstance() {
        return new BeerListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        RxJavaPlugins.setErrorHandler(Throwable::printStackTrace);
        presenter.setView(this);
        presenter.start();
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(BEER_LIST_FRAGMENT, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        beerListRecyclerView.setLayoutManager(layoutManager);
        beerListRecyclerView.setAdapter(beerListAdapter);
        beerListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int totalItemCount = layoutManager.getItemCount();
                final int lastItem = layoutManager.findLastVisibleItemPosition();
                if (lastItem >= totalItemCount - 1) {
                    presenter.getAdditionalMovies(++page);
                }
            }
        });
    }

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        noResultsTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem randomBeerItem = menu.findItem(R.id.action_get_random_beer);
        randomBeerItem.setOnMenuItemClickListener(randomBeerOnClickListener);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    public void render(BeersViewModel beersViewModel) {
        beerListAdapter.setBeers(beersViewModel);
    }

    @Override
    public void showRandomBeerDialog(final BeerViewModel beerViewModel, final MenuItem item) {
        View layout = getLayoutInflater().inflate(POPUP_LAYOUT, (ViewGroup) getView(), false);
        final BeerDetailsDialog beerDetailsPopupWindow = new BeerDetailsDialog(getContext());
        beerDetailsPopupWindow.setContentView(layout);
        beerDetailsPopupWindow.showDialog(beerViewModel, imageLoader);
        item.setOnMenuItemClickListener(randomBeerOnClickListener);
    }

    private final MenuItem.OnMenuItemClickListener randomBeerOnClickListener = item -> {
        item.setOnMenuItemClickListener(null);
        presenter.getRandomBeer(item);
        return false;
    };
}
