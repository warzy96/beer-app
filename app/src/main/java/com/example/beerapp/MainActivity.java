package com.example.beerapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.beerapp.di.activity.ActivityComponent;
import com.example.beerapp.di.activity.DaggerActivity;
import com.example.beerapp.router.Router;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends DaggerActivity {

    private final String DEFAULT_TITLE = "BeerApp";

    @Inject
    Router router;

    @BindView(R.id.appbar)
    Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(appBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(DEFAULT_TITLE);
        }
        router.showBeerListScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu_items, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search_beers);
        searchMenuItem.setVisible(true);
        searchMenuItem.setOnMenuItemClickListener(item -> {
            router.showSearchBeerScreen();
            return true;
        });

        MenuItem randomBeerMenuItem = menu.findItem(R.id.action_get_random_beer);
        randomBeerMenuItem.setVisible(true);
        randomBeerMenuItem.setOnMenuItemClickListener(item -> {
            router.showRandomBeerScreen();
            return true;
        });

        MenuItem dateItemMenu = menu.findItem(R.id.action_set_brewed_before_date);
        dateItemMenu.setVisible(false);
        return true;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
