package birkan.appcentdemoapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import birkan.appcentdemoapp.Manifest;
import birkan.appcentdemoapp.Utility.Constant;
import birkan.appcentdemoapp.Utility.LocManager;
import birkan.appcentdemoapp.R;
import birkan.appcentdemoapp.Utility.GPSManager;
import birkan.appcentdemoapp.Utility.Util;

public class MainActivity extends Activity {

    private LocManager locManager;
    private GPSManager gpsManager;
    private Activity mainActivity;

    private String adress = "";
    private double[] coord;

    private TextView locationTxt;
    private ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this;

        searchButton = findViewById(R.id.srcBtn);
        locationTxt = findViewById(R.id.locTxt);

        if(Util.isPermissionGranted(mainActivity))                      // en başta lokasyona erişim için izin al.
        {
            initActivity();
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Util.isPermissionGranted(mainActivity)) {           // butona tıklandığında izinleri kontrol et / yoksa izin al.
                    startListActivity(coord);
                } else {
                    Toast.makeText(getApplicationContext(), "Konum izini verilmedi.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initActivity(){

        gpsManager = new GPSManager(getApplicationContext());
        locManager = new LocManager(getApplicationContext());

        if(gpsManager.isGPSOpened()){          // eğer GPS açıksa lokasyon al ve textview'e setle.
            try{
                adress = locManager.getCoordAdress();       // koordinat bilgilerini alır.
                coord = locManager.getCoord();              // koordinatın adresini alır.
            }catch (Exception e){
                Toast.makeText(this, "Koordinat bilgileri alınamadı.", Toast.LENGTH_SHORT).show();
            }
            locationTxt.setText(adress);                // textview a buldugumuz adresi eşitle.

        }else{                                 // GPS açık değilse GPS ayarlarına intent at.
            Toast.makeText(this, "Konuma erişmek için GPS açınız..", Toast.LENGTH_SHORT).show();
            startActivityForResult(new Intent(Constant.ACTION_LOC_SETTINGS), 200);
        }
    }

    private void startListActivity(final double[] coord)    // search butonuna tıklandıgında çalışacak fonksiyon.
    {
        Intent placeListIntent = new Intent(getBaseContext(), PlaceListActivity.class);   // yeni intent oluştur
        placeListIntent.putExtra("placeCoord", coord);                                   // intent'e şuanki konumu ekle
        startActivity(placeListIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){             // attığımız intentten gelen response,
            if(gpsManager.isGPSOpened()){   // eğer gps ayarlardan açıldıysa
                initActivity();
            }else{                         // gps açılmadıysa
                Toast.makeText(this, "Lütfen GPS açınız.", Toast.LENGTH_LONG).show();
            }
        }
    }


    // izin isteme request'inden gelen sonuç.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 201){
            if(Util.isPermissionGranted(mainActivity))
                initActivity();
            else Toast.makeText(this, "Lütfen konum erişimine izin veriniz.", Toast.LENGTH_SHORT).show();
        }
    }

}
