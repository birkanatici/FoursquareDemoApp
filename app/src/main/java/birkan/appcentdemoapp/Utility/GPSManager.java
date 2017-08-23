package birkan.appcentdemoapp.Utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;

import birkan.appcentdemoapp.Activity.MainActivity;
import birkan.appcentdemoapp.R;

/**
 * Created by birkan on 19.08.2017.
 */

public class GPSManager {

    private Context activity;

    public GPSManager(Context _activity){
        activity = _activity;
    }

    public boolean isGPSOpened(){
        String provider = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(provider.contains("gps"))
            return true;
        else
            return false;
    }
}
