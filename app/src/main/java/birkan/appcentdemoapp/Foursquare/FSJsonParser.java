package birkan.appcentdemoapp.Foursquare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import birkan.appcentdemoapp.Object.Place;

/**
 * Created by birkan on 19.08.2017.
 */

public class FSJsonParser {

    private JSONObject jsonObject;

    public FSJsonParser(JSONObject _jsonObject) {
        this.jsonObject = _jsonObject;
    }

    public ArrayList<Place> parse(){

        JSONArray placeArr = new JSONArray();
        ArrayList<Place> placeList = new ArrayList<>();

        try {
            placeArr  = jsonObject.getJSONObject("response").getJSONArray("groups").getJSONObject(0).getJSONArray("items");
        } catch (JSONException e) {

        }

        for(int i=0; i< placeArr.length(); i++){

            JSONObject ob = new JSONObject();

            Place place = new Place();

            try {
                ob = placeArr.getJSONObject(i).getJSONObject("venue");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                place.setpName(ob.getString("name"));                                  // mekan ismi
                place.setpAdress(ob.getJSONObject("location").getJSONArray("formattedAddress").get(0).toString().replaceAll("\n"," "));     // mekan adresi
                place.setpLat(ob.getJSONObject("location").getDouble("lat"));                                    // mekan lat
                place.setpLng(ob.getJSONObject("location").getDouble("lng"));                                    // mekan long
                place.setpIcon(ob.getJSONArray("categories").getJSONObject(0).getJSONObject("icon").getString("prefix") + "64"
                                + ob.getJSONArray("categories").getJSONObject(0).getJSONObject("icon").getString("suffix"));   //mekan icon adresi
                place.setpDistance((double) ob.getJSONObject("location").getInt("distance") / 1000);   // mekan a olan uzaklık

                placeList.add(place);

            }catch (JSONException e) {
                continue;
            }
        }

        return placeList;
    }

}
