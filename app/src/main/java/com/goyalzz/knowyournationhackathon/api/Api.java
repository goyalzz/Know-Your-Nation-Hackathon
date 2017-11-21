package com.goyalzz.knowyournationhackathon.api;

import com.goyalzz.knowyournationhackathon.dto.CountryDetailsDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("/rest/v2/all")
    Observable<List<CountryDetailsDto>> getAllCountriesList();
}
