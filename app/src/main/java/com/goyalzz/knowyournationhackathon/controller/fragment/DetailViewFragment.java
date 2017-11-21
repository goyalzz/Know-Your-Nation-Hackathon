package com.goyalzz.knowyournationhackathon.controller.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.goyalzz.knowyournationhackathon.R;
import com.goyalzz.knowyournationhackathon.databinding.DetailViewFragmentBinding;
import com.goyalzz.knowyournationhackathon.dto.CountryDetailsDto;

/**
 * Created by ankush on 21/11/17.
 */

public class DetailViewFragment extends Fragment {

    private CountryDetailsDto countryDetailsDto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DetailViewFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.detail_view_fragment, container, Boolean.FALSE);
        countryDetailsDto = new CountryDetailsDto();
        Bundle arguments = getArguments();
        if (arguments!= null && arguments.containsKey("CountryDetails")) {
            countryDetailsDto = (CountryDetailsDto) arguments.getSerializable("CountryDetails");
        }
        binding.setDataSet(countryDetailsDto);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WebView flag = view.findViewById(R.id.flag_web);
        flag.setInitialScale(1);
        flag.getSettings().setLoadWithOverviewMode(true);
        flag.getSettings().setUseWideViewPort(true);
        // fit the width of screen
        flag.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // remove a weird white line on the right size
        flag.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        if (countryDetailsDto.getFlag() != null && !countryDetailsDto.getFlag().isEmpty()) {
            flag.loadUrl(countryDetailsDto.getFlag());
        }

    }
}
