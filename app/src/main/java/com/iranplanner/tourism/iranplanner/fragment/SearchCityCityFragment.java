package com.iranplanner.tourism.iranplanner.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.iranplanner.tourism.iranplanner.R;

import java.util.List;

import autoComplet.MyFilterableAdapter;
import autoComplet.readJsonCity;
import entity.City;

public class SearchCityCityFragment extends Fragment {

    public SearchCityCityFragment() {
    }

    public static SearchCityCityFragment newInstance() {
        SearchCityCityFragment fragment = new SearchCityCityFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_city_city, container, false);
        AutoCompleteTextView fromCity_city = (AutoCompleteTextView) view.findViewById(R.id.fromCity_city);
        autoComplete(fromCity_city);
        return view;
    }

    public void autoComplete(AutoCompleteTextView city) {
        List<City> cities;
        readJsonCity readJsonCity = new readJsonCity();
        cities = readJsonCity.getListOfCity(getContext());
        MyFilterableAdapter adapter = new MyFilterableAdapter(getContext(), android.R.layout.simple_list_item_1, cities);
        city.setAdapter(adapter);
    }

}
