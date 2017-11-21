package com.goyalzz.knowyournationhackathon.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.goyalzz.knowyournationhackathon.dto.CountryDetailsDto;

/**
 * Created by ankush on 17/10/17.
 */

@Database(entities = {CountryDetailsDto.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CountryDetailsDao countryDetailsDao();

}