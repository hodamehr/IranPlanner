package autoComplet;

import android.content.Context;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import entity.City;

/**
 * Created by h.vahidimehr on 08/01/2017.
 */

public class readJsonCity {


    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("cities.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<City> getListOfCity(Context context) {
        List<City> list = new ArrayList<City>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("ListOfCity");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                City city = new City();
                city.setCityId(jo_inside.getString("city_id"));
                city.setCityName(jo_inside.getString("city_name"));
//                city.setCityProvince(jo_inside.getString("city_province"));
//                city.setLang(jo_inside.getString("latitude"));
//                city.setLatitude(jo_inside.getString("longitude"));
//                city.setLongitude(jo_inside.getString("lang"));
                list.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }


}
