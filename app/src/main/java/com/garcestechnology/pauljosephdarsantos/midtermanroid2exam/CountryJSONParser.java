package com.garcestechnology.pauljosephdarsantos.midtermanroid2exam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pauljosephdarsantos on 2/6/17.
 */

public class CountryJSONParser {
    public List<HashMap<String, Object>> parse(JSONObject jObject) {
        JSONArray jCountries = null;
        try {
            jCountries = jObject.getJSONObject("results").getJSONObject("albummatches").getJSONArray("album");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getCountries(jCountries);
    }

    private List<HashMap<String, Object>> getCountries(JSONArray jCountries) {
        int countryCount = jCountries.length();
        List<HashMap<String, Object>> countryList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> country = null;

        for (int i = 0; i < countryCount; i++) {
            try {
                country = getCountry((JSONObject) jCountries.get(i));
                countryList.add(country);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return countryList;
    }

    private HashMap<String, Object> getCountry(JSONObject jCountry) {
        HashMap<String, Object> country = new HashMap<String, Object>();
        String countryName = "";
        String flag = "";
        String language = "";


        try {
            countryName = jCountry.getString("name");
            flag = jCountry.getJSONArray("image").getJSONObject(0).getString("#text");
            language = jCountry.getString("artist");


            String details = language;

            country.put("country", countryName);
            country.put("flag", R.mipmap.ic_launcher);
            country.put("flag_path", flag);
            country.put("details", details);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return country;
    }

}
