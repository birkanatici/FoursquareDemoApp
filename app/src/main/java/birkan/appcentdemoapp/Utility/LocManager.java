package birkan.appcentdemoapp.Utility;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import birkan.appcentdemoapp.Activity.MainActivity;

/**
 * Created by birkan on 18.08.2017.
 */

public class LocManager extends Activity {

    private Context mainContext;
    private LocationManager lm;
    private List<String> providers;
    private Location location;

    public LocManager(Context _context) {

        this.mainContext = _context;
        lm = (LocationManager) mainContext.getSystemService(Context.LOCATION_SERVICE);

        providers = lm.getProviders(true);   // lokasyon sağlayan servisler : gps, network, passive

        location = null;

        for (int i = providers.size() - 1; i >= 0; i--) {
            checkPermission();
            location = lm.getLastKnownLocation(providers.get(i));
            if (location != null) break;
        }
    }

    // koordinat bilgilerini alır.
    public double[] getCoord() {
        double[] coord = new double[2];
        if (location != null) {
            coord[0] = location.getLatitude();
            coord[1] = location.getLongitude();
        }
        return coord;
    }


    // koordinat bilgilerini adrese çevirir.
   public String getCoordAdress(){
        Geocoder geocoder = new Geocoder(mainContext, Locale.getDefault());

        List<Address> addresses  = null;

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            Toast.makeText(mainContext, "Adres bilgisi alınamadı.", Toast.LENGTH_LONG).show();
            return "";
        }

        String area = addresses.get(0).getSubAdminArea();
        String city = addresses.get(0).getAdminArea();

        if(area == null) area = "";
        if(city == null) city = "";

        String result = area + ", "+ city;

        return result;
    }

    // izinlerin alınıp alınmadığını kontrol eder.
    private boolean checkPermission()
    {
        if(Build.VERSION.SDK_INT >= 23
                && ContextCompat.checkSelfPermission(mainContext, Constant.permissions[0])
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(mainContext, Constant.permissions[1])
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(Constant.permissions, 100);    // izin verilmediyse izin iste

            return true;
        }
        // false dönerse izin verildi.
        return false;
    }
}
