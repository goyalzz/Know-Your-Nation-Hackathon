package com.goyalzz.knowyournationhackathon.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.goyalzz.knowyournationhackathon.dto.CountryDetailsDto;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by ankush on 21/11/17.
 */

@Dao
public interface CountryDetailsDao {

    @Query("SELECT * FROM CountryDetails")
    Maybe<List<CountryDetailsDto>> getAll();

    @Query("SELECT * FROM CountryDetails WHERE name LIKE :countryName")
    Maybe<List<CountryDetailsDto>> getAllByCountryName(String countryName);

    @Insert
    void insertAll(CountryDetailsDto... countryDetailsDtos);

    @Insert
    Long insert(CountryDetailsDto countryDetailsDto);

    @Delete
    void delete(CountryDetailsDto countryDetailsDto);

    @Update
    void updateUsers(CountryDetailsDto... countryDetailsDtos);

}
