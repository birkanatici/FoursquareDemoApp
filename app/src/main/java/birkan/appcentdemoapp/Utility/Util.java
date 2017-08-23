package birkan.appcentdemoapp.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by birkan on 19.08.2017.
 */

public class Util {

    public static String getFoursquareApiUrl(double latitude, double longitude){
        String result = "";
        result = Constant.FOURSQUARE_API + "&ll=" + latitude
                                         + "," +longitude
                                         + "&client_id=" + Constant.CLIENT_ID
                                         + "&client_secret=" + Constant.CLIENT_SECRET;
        return result;
    }

    public static boolean isPermissionGranted(Activity _activity){
        if (ActivityCompat.checkSelfPermission(_activity, Constant.permissions[0]) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(_activity, Constant.permissions[1]) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(_activity,Constant.permissions, 201);
            return false;
        }else{
            return true;
        }
    }

}
