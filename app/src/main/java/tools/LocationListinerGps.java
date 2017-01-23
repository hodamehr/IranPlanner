package tools;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by h.vahidimehr on 10/01/2017.
 */

public class LocationListinerGps implements LocationListener {
    Context context;
    String cityName = null;

    public LocationListinerGps(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location loc) {
        Locale locale=new Locale("fa");
        Geocoder gcd = new Geocoder(context, locale);
        List<Address> addresses = null;
        try {
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}

