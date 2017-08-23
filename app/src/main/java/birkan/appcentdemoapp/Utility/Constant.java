package birkan.appcentdemoapp.Utility;

import android.Manifest;
import android.provider.Settings;

import java.net.PortUnreachableException;

/**
 * Created by birkan on 19.08.2017.
 */

public class Constant {

    // lokasyon almak için gerekli izinler.
    public static String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public static final String CLIENT_ID = "X4OO2JMW5TNCRVM525A5OTLUWVC1U451EYMJ3WS032ZAWDGE";
    public static String CLIENT_SECRET = "YONZQVUZSX3PVN11WEQPLXWA0V24FDQIEDNSCYPPPS00X1QP";
    public static final String FOURSQUARE_API = "https://api.foursquare.com/v2/venues/explore?v=20161016";

    public static final String ACTION_LOC_SETTINGS = Settings.ACTION_LOCATION_SOURCE_SETTINGS;


}
