package com.goyalzz.knowyournationhackathon.controller.fragment;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.goyalzz.knowyournationhackathon.R;
import com.goyalzz.knowyournationhackathon.adapters.CountryListViewAdapter;
import com.goyalzz.knowyournationhackathon.api.RetrofitConfig;
import com.goyalzz.knowyournationhackathon.controller.activity.HomeActivity;
import com.goyalzz.knowyournationhackathon.databinding.CountryListFragmentBinding;
import com.goyalzz.knowyournationhackathon.db.AppDatabase;
import com.goyalzz.knowyournationhackathon.dto.CountryDetailsDto;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ankush on 20/11/17.
 */

public class CountryListFragment extends Fragment implements CountryListViewAdapter.ItemClick {

    private CountryListViewAdapter adapter;

    private AppDatabase dataBase;

    private ProgressDialog progressDialog;

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            if (s.length() > 0)
                adapter.replace(dataBase.countryDetailsDao().getAllByCountryName(s + "%").blockingGet());
            return true;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            if (s.length() == 0)
                adapter.add(dataBase.countryDetailsDao().getAll().blockingGet());
            return true;
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CountryListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.country_list_fragment, container, Boolean.FALSE);
        binding.setHandlers(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((SearchView) view.findViewById(R.id.search_country_name)).setOnQueryTextListener(queryTextListener);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new CountryListViewAdapter(getContext(), new ArrayList<CountryDetailsDto>(), this);
        recyclerView.setAdapter(adapter);

        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        if (!PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(HomeActivity.ONETIMESYNC, Boolean.FALSE)) {
            progressDialog.setTitle("Syncing OneTime Data");
            progressDialog.setMessage("Syncing ...");
            progressDialog.show();
            fetchOnlineData();
        } else {
            progressDialog.setTitle("Loading Data form Local DB");
            progressDialog.setMessage("Loading ...");
            progressDialog.show();
            adapter.add(dataBase.countryDetailsDao().getAll().blockingGet());
            progressDialog.dismiss();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataBase = ((HomeActivity) context).getDataBase();
    }

    @Override
    public void onItemClick(CountryDetailsDto data) {
        DetailViewFragment fragment = new DetailViewFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable("CountryDetails", data);
        fragment.setArguments(arguments);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
                fragment, "DetailViewFragment").addToBackStack("DetailViewFragment").commit();
    }

    private void fetchOnlineData() {

        RetrofitConfig.getInstance().getAllCountriesList()
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CountryDetailsDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<CountryDetailsDto> countryDetailsDtos) {
                        if (adapter != null)
                            adapter.add(countryDetailsDtos);
                        PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(HomeActivity.ONETIMESYNC, Boolean.TRUE).apply();
                        dataBase.countryDetailsDao().insertAll(countryDetailsDtos.toArray(new CountryDetailsDto[countryDetailsDtos.size()]));
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            progressDialog.dismiss();
                        } catch (Exception ex) {

                        }
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        try {
                            progressDialog.dismiss();
                        } catch (Exception e) {

                        }
                    }
                });
    }

}
