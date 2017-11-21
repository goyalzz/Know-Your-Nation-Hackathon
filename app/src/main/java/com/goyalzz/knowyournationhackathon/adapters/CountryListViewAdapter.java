package com.goyalzz.knowyournationhackathon.adapters;

import android.content.Context;

import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.goyalzz.knowyournationhackathon.R;
import com.goyalzz.knowyournationhackathon.dto.CountryDetailsDto;
import com.goyalzz.knowyournationhackathon.dto.LanguageDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankush on 20/11/17.
 */

public class CountryListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<CountryDetailsDto> dataList = new ArrayList<>();

    private ItemClick clickListener;

    public CountryListViewAdapter(Context context, List<CountryDetailsDto> dataList, ItemClick clickListener) {
        this.context = context;
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryRowViewHolder(LayoutInflater.from(context).inflate(R.layout.country_list_row, parent, Boolean.FALSE));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CountryRowViewHolder) {
            ((CountryRowViewHolder) holder).onBindUi(dataList.get(position));
        }
    }

    private class CountryRowViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName, nativeName, nativeLanguages;

        private WebView flag;

        private CountryRowViewHolder(View view) {
            super(view);
            ((ImageView) view.findViewById(R.id.check_in)).setImageDrawable(VectorDrawableCompat.create(context.getResources(), R.drawable.ic_check_in_arrow, null));
            flag = view.findViewById(R.id.flag_img);
            flag.setInitialScale(1);
            flag.getSettings().setLoadWithOverviewMode(true);
            flag.getSettings().setUseWideViewPort(true);
            // fit the width of screen
            flag.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
// remove a weird white line on the right size
            flag.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

            countryName = view.findViewById(R.id.country_name);
            nativeName = view.findViewById(R.id.native_name);
            nativeLanguages = view.findViewById(R.id.native_languages);
        }

        private void onBindUi(final CountryDetailsDto data) {

            if (data.getFlag() != null && !data.getFlag().isEmpty()) {
                flag.loadUrl(data.getFlag());
            }

            countryName.setText(data.getName());
            nativeName.setText(data.getNativeName());
            StringBuilder sb = new StringBuilder();
            for (LanguageDetails lang : data.getLanguages()) {
                sb.append(lang.getNativeName()).append(", ");
            }
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);
            nativeLanguages.setText(sb.toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null)
                        clickListener.onItemClick(data);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void add(List<CountryDetailsDto> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void add(CountryDetailsDto data) {
        this.dataList.add(data);
        notifyDataSetChanged();
    }

    public void replace(List<CountryDetailsDto> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

        public interface ItemClick {
        void onItemClick(CountryDetailsDto data);
    }

}
