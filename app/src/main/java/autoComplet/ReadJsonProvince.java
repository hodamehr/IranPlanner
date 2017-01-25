package autoComplet;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import entity.Province;

/**
 * Created by Hoda on 24/01/2017.
 */
public class ReadJsonProvince {
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("province.json");

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

    public List<Province> getListOfProvience(Context context) {
        List<Province> list = new ArrayList<Province>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("ListOfProvince");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Province province = new Province();
                province.setProvinceId(jo_inside.getString("province_id"));
                province.setProvinceName(jo_inside.getString("province_name"));
                list.add(province);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
