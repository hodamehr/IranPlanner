package autoComplet;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import entity.Attraction;

/**
 * Created by h.vahidimehr on 05/02/2017.
 */

public class ReadJsonAttraction {

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("attraction.json");

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

    public List<Attraction> getListOfAttractin(Context context) {
        List<Attraction> list = new ArrayList<Attraction>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("ListOfAttraction");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Attraction attraction = new Attraction();
                attraction.setAttractionId(jo_inside.getString("attraction_id"));
                attraction.setAttractionName(jo_inside.getString("attraction_name"));
                list.add(attraction);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
