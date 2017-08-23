package birkan.appcentdemoapp.Foursquare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import birkan.appcentdemoapp.Activity.MainActivity;
import birkan.appcentdemoapp.Object.Place;
import birkan.appcentdemoapp.Utility.Util;

/**
 * Created by birkan on 19.08.2017.
 */

public class FSTask extends AsyncTask<Void, Void, ArrayList<Place>> {

    private double[] coord;
    private String foursquareApiUrl;
    private ArrayList<Place> places;
    private ProgressDialog dialog;
    private Context mainContext;

    public FSTask(double[] _coord, Context _context){
        this.coord = _coord;
        foursquareApiUrl = Util.getFoursquareApiUrl(coord[0], coord[1]);    // koordinatlar ile dinamik foursquare api urlsini aldık
        this.mainContext = _context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(mainContext);
        dialog.setMessage("Mekanlar getiriliyor, Lütfen Bekleyiniz..");
        dialog.show();
    }

    @Override
    protected ArrayList<Place> doInBackground(Void... voids) {

        places = new ArrayList<Place>();

        try {

            HttpGet httppost = new HttpGet(this.foursquareApiUrl);      // apiye request atar
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);       // response burda tutar.

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();      // response a gelen statü kodu

            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(data);         // responsedan JSON objesi oluşturduk

                FSJsonParser parser = new FSJsonParser(jsonObject);    // oluşan json objesini parse eder
                places = parser.parse();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return places;                  // parse işleminden sonra oluşan mekanlar listesini dönzerir.
    }

    @Override
    protected void onPostExecute(ArrayList<Place> places) {
        dialog.dismiss();
    }
}
