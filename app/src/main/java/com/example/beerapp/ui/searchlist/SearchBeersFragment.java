package com.example.beerapp.ui.searchlist;

import android.app.DatePickerDialog;
import android.content.Context;
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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beerapp.R;
import com.example.beerapp.di.activity.ForActivity;
import com.example.beerapp.di.fragment.FragmentComponent;
import com.example.beerapp.ui.base.BaseFragment;
import com.example.beerapp.ui.beerlist.BeerListAdapter;
import com.example.beerapp.ui.beerlist.BeersViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class SearchBeersFragment extends BaseFragment<SearchBeersContract.Presenter> implements SearchBeersContract.View {

    public static final String TAG = "BeerSearchListFragment";

    private final Calendar brewedBeforeCalendar = Calendar.getInstance();

    @Inject
    BeerListAdapter beerListAdapter;

    @Inject
    @ForActivity
    Context context;

    @BindView(R.id.beer_list_recycler_view)
    RecyclerView beerListRecyclerView;

    @BindView(R.id.no_results_text_view)
    TextView noResultsTextView;

    @LayoutRes
    private static final int BEER_LIST_FRAGMENT = R.layout.fragment_beer_list;

    public static SearchBeersContract.View newInstance() {
        return new SearchBeersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter.setView(this);
    }

    @NotNull
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

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search_beers);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchItem.setOnMenuItemClickListener(null);
        searchView.setOnQueryTextListener(listener);
        searchItem.setOnActionExpandListener(onActionExpandListener);

        MenuItem dateItem = menu.findItem(R.id.action_set_brewed_before_date);
        dateItem.setVisible(true);
        dateItem.setOnMenuItemClickListener(dateItemOnClickListener);

        MenuItem randomBeerItem = menu.findItem(R.id.action_get_random_beer);
        randomBeerItem.setVisible(false);

        searchItem.expandActionView();

        super.onPrepareOptionsMenu(menu);
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        beerListRecyclerView.setLayoutManager(layoutManager);
        beerListRecyclerView.setAdapter(beerListAdapter);
    }

    @Override
    public void render(BeersViewModel beersViewModel) {
        beerListAdapter.setBeers(beersViewModel);
        if(beersViewModel.getBeerViewModelList().isEmpty()) {
            noResultsTextView.setVisibility(View.VISIBLE);
        } else {
            noResultsTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    private final SearchView.OnQueryTextListener listener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            presenter.start(query, getBrewedBeforeDate());
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private String getBrewedBeforeDate() {
        return (brewedBeforeCalendar.get(Calendar.MONTH) + 1) + "-" + brewedBeforeCalendar.get(Calendar.YEAR);
    }

    private final MenuItem.OnMenuItemClickListener dateItemOnClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            final DatePickerDialog datePickerDialog = new DatePickerDialog(context, onDateSetListener, brewedBeforeCalendar.get(Calendar.YEAR), brewedBeforeCalendar.get(Calendar.MONTH), brewedBeforeCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
            return false;
        }
    };

    private final DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, month, dayOfMonth) -> {
        brewedBeforeCalendar.set(Calendar.YEAR, year);
        brewedBeforeCalendar.set(Calendar.MONTH, month);
    };

    private final MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
        @Override
        public boolean onMenuItemActionExpand(MenuItem item) {
            return true;
        }

        @Override
        public boolean onMenuItemActionCollapse(MenuItem item) {
            final FragmentManager fragmentManager = getFragmentManager();
            if(fragmentManager != null) {
                fragmentManager.popBackStack();
            }
            return false;
        }
    };
}
