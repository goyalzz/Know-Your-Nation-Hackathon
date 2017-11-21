package com.goyalzz.knowyournationhackathon.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ankush on 20/11/17.
 */

@Entity(tableName = "CountryDetails")
public class CountryDetailsDto implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String name;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(StringListToGsonConverter.class)
    private List<String> topLevelDomain;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String alpha2Code;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String alpha3Code;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(StringListToGsonConverter.class)
    private List<String> callingCodes;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String capital;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(StringListToGsonConverter.class)
    private List<String> altSpellings;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String region;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String subregion;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String population;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(DoubleListToGsonConverter.class)
    private List<Double> latlng;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String demonym;

    @ColumnInfo(typeAffinity = ColumnInfo.REAL)
    private Double area;

    @ColumnInfo(typeAffinity = ColumnInfo.REAL)
    private Double gini;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(StringListToGsonConverter.class)
    private List<String> timezones;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(StringListToGsonConverter.class)
    private List<String> borders;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String nativeName;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String numericCode;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(CurrencyListToGsonConverter.class)
    private List<CurrencyDetails> currencies;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(LanguageListToGsonConverter.class)
    private List<LanguageDetails> languages;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    @TypeConverters(MapToGsonConverter.class)
    private Map<String, String> translations;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String flag;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    private String cioc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getGini() {
        return gini;
    }

    public void setGini(Double gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<CurrencyDetails> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDetails> currencies) {
        this.currencies = currencies;
    }

    public List<LanguageDetails> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDetails> languages) {
        this.languages = languages;
    }

    public Map<String, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<String, String> translations) {
        this.translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    @Override
    public String toString() {
        return "CountryDetailsDto{" +
                "name='" + name + '\'' +
                ", topLevelDomain=" + topLevelDomain +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", callingCodes=" + callingCodes +
                ", capital='" + capital + '\'' +
                ", altSpellings=" + altSpellings +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population='" + population + '\'' +
                ", latlng=" + latlng +
                ", demonym='" + demonym + '\'' +
                ", area=" + area +
                ", gini=" + gini +
                ", timezones=" + timezones +
                ", borders=" + borders +
                ", nativeName='" + nativeName + '\'' +
                ", numericCode='" + numericCode + '\'' +
                ", currencies=" + currencies +
                ", translations=" + translations +
                ", flag='" + flag + '\'' +
                ", cioc='" + cioc + '\'' +
                '}';
    }

    public static class StringListToGsonConverter{
        @TypeConverter
        public static List<String> restoreList(String listOfString){
            return new Gson().fromJson(listOfString, new TypeToken<List<String>>() {}.getType());
        }

        @TypeConverter
        public static String saveListOfString(List<String> listOfString){
            return new Gson().toJson(listOfString);
        }

        @TypeConverter
        public static Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public static Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }
    }

    public static class DoubleListToGsonConverter {
        @TypeConverter
        public static List<Double> restoreList(String listOfString) {
            return new Gson().fromJson(listOfString, new TypeToken<List<Double>>() {
            }.getType());
        }

        @TypeConverter
        public static String saveListOfString(List<Double> listOfString) {
            return new Gson().toJson(listOfString);
        }
    }

    public static class CurrencyListToGsonConverter {
        @TypeConverter
        public static List<CurrencyDetails> restoreList(String list) {
            return new Gson().fromJson(list, new TypeToken<List<CurrencyDetails>>() {
            }.getType());
        }

        @TypeConverter
        public static String saveListOfString(List<CurrencyDetails> list) {
            return new Gson().toJson(list);
        }
    }

    public static class LanguageListToGsonConverter {
        @TypeConverter
        public static List<LanguageDetails> restoreList(String list) {
            return new Gson().fromJson(list, new TypeToken<List<LanguageDetails>>() {
            }.getType());
        }

        @TypeConverter
        public static String saveListOfString(List<LanguageDetails> list) {
            return new Gson().toJson(list);
        }
    }

    public static class MapToGsonConverter {
        @TypeConverter
        public static Map<String, String> restoreList(String map) {
            return new Gson().fromJson(map, new TypeToken<Map<String, String>>() {
            }.getType());
        }

        @TypeConverter
        public static String saveListOfString(Map<String, String> map) {
            return new Gson().toJson(map);
        }
    }
}
