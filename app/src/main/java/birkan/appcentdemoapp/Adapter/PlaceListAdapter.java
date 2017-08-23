package birkan.appcentdemoapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import birkan.appcentdemoapp.Object.Place;
import birkan.appcentdemoapp.R;

import static android.R.attr.radius;
import static android.R.attr.track;

/**
 * Created by birkan on 21.08.2017.
 */

public class PlaceListAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private List<Place> placeList;

    public PlaceListAdapter(Activity _activity, ArrayList<Place> _placeList){
        this.placeList = _placeList;

        mInflater = (LayoutInflater) _activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return placeList.size();
    }

    @Override
    public Object getItem(int i) {
        return placeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View satirView;

        satirView = mInflater.inflate(R.layout.place_list_item, null);

        TextView placeName = (TextView) satirView.findViewById(R.id.placeName);
        TextView placeAdress = (TextView) satirView.findViewById(R.id.placeAdress);
        TextView placeDistace = (TextView) satirView.findViewById(R.id.placeDistance);
        ImageView placeIcon =(ImageView) satirView.findViewById(R.id.placeIcon);

        Place place = placeList.get(i);             //satıra eklenecek mekanı belirledik
        placeName.setText(place.getpName());        // mekan adı
        placeAdress.setText(place.getpAdress());    // mekan adresi

        NumberFormat formatter = new DecimalFormat("#0.00");
        placeDistace.setText(formatter.format(place.getpDistance()) + " km");   // mekan uzaklığı

        try{
            Picasso.with(satirView.getContext()).load(place.getpIcon()).into(placeIcon);    // mekan iconu
        }catch (Exception e){

        }

        return satirView;
    }
}
