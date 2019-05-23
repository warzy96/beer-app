package com.example.beerapp.ui.beerlist;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beerapp.R;
import com.example.beerapp.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public final class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerItemViewHolder> {

    private final LayoutInflater layoutInflater;
    private final ImageLoader imageLoader;
    private final Context context;

    private List<BeerViewModel> beerViewModelList = new ArrayList<>();

    @LayoutRes
    private static final int ITEM_BEER_LAYOUT = R.layout.item_beer_layout;

    public BeerListAdapter(final LayoutInflater layoutInflater, final ImageLoader imageLoader, final Context context) {
        this.layoutInflater = layoutInflater;
        this.imageLoader = imageLoader;
        this.context = context;
    }

    @NonNull
    @Override
    public BeerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BeerItemViewHolder(layoutInflater.inflate(ITEM_BEER_LAYOUT, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BeerItemViewHolder holder, int position) {
        holder.render(beerViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return beerViewModelList.size();
    }

    public void setBeers(BeersViewModel beersViewModel) {
        beerViewModelList.clear();
        beerViewModelList.addAll(beersViewModel.getBeerViewModelList());
        notifyDataSetChanged();
    }

    class BeerItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_beer_title_text_view)
        TextView beerItemTitleView;

        @BindView(R.id.item_beer_image_view)
        ImageView beerImageView;

        @BindView(R.id.item_beer_description_text_view)
        TextView beerDescriptionView;

        @BindDimen(R.dimen.circular_progressbar_stroke_width)
        float circularProgressbarStrokeWidth;

        @LayoutRes
        private final static int POPUP_LAYOUT = R.layout.beer_details_popup_layout;

        public BeerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void render(final BeerViewModel beerViewModel) {
            beerItemTitleView.setText(beerViewModel.getName());
            beerDescriptionView.setText(beerViewModel.getDescription());
            imageLoader.renderImage(beerViewModel.getImageUrl(), beerImageView, circularProgressbarStrokeWidth);
            itemView.setOnClickListener(view -> onItemClickListener.onClick(beerViewModel, view));
        }

        private BeerOnClickListener onItemClickListener = new BeerOnClickListener() {

            @Override
            public void onClick(BeerViewModel beerViewModel, View view) {
                View layout = layoutInflater.inflate(POPUP_LAYOUT, (ViewGroup) view.getParent(), false);
                final BeerDetailsDialog beerDetailsPopupWindow = new BeerDetailsDialog(context);
                beerDetailsPopupWindow.setContentView(layout);
                beerDetailsPopupWindow.showDialog(beerViewModel, imageLoader);
            }
        };


    }

    private interface BeerOnClickListener {

        void onClick(BeerViewModel beerViewModel, View view);
    }
}
