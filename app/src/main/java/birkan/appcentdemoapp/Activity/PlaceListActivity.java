package birkan.appcentdemoapp.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import birkan.appcentdemoapp.Adapter.PlaceListAdapter;
import birkan.appcentdemoapp.Foursquare.FSTask;
import birkan.appcentdemoapp.Object.Place;
import birkan.appcentdemoapp.R;

public class PlaceListActivity extends Activity {

    private ArrayList<Place> places = new ArrayList<>();        // mekanların tutulacağı liste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        setTitle("Yakın Mekanlar");

        final ListView pListView = (ListView) findViewById(R.id.placeListView);  // mekanların gösterileceği listview
        ProgressBar progressBar = findViewById(R.id.loadingBar);
        progressBar.setVisibility(View.VISIBLE);

        double[] coord = (double[]) getIntent().getExtras().get("placeCoord");   // main aktiviteden gelen bundle verisi

        FSTask foursquareTask = new FSTask(coord, this);   // foursquare apisine koordinat veriyoruz.

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                places = foursquareTask.execute().get();    // foursquare apisinden asyntask ile mekanları al.
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // listview için özelleştirdiğimiz adapter
        PlaceListAdapter pAdapter = new PlaceListAdapter(this, places);          // çektiğimiz mekanları veriyoruz
        pListView.setAdapter(pAdapter);                                          // listview'a adapteri setliyoruz.

        progressBar.setVisibility(View.GONE);


        // listviewdan item'a tıklandığını handle eden fonksiyon
        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Place place = places.get(position);         // listviewdan hangi mekana tıklandıysa onu alır.
                Toast.makeText(getBaseContext(), place.getpName(), Toast.LENGTH_SHORT).show();

                Uri uri = Uri.parse("geo:0,0?q="+place.getpLat()+","+place.getpLng()+"("+place.getpName()+")");  // map apisine gönderilcek koordinat bilgileri
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);     // koordinatı haritada açmak için intent atıyoruz.
            }
        });

    }


}
