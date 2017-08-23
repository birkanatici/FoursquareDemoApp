package birkan.appcentdemoapp.Object;

/**
 * Created by birkan on 19.08.2017.
 */

public class Place {

    private String pName;
    private String pAdress;
    private double pLat;
    private double pLng;
    private String pIcon;
    private double pDistance;

    public double getpDistance() {
        return pDistance;
    }

    public void setpDistance(double pDistance) {
        this.pDistance = pDistance;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAdress() {
        return pAdress;
    }

    public void setpAdress(String pAdress) {
        this.pAdress = pAdress;
    }

    public double getpLat() {
        return pLat;
    }

    public void setpLat(double pLat) {
        this.pLat = pLat;
    }

    public double getpLng() {
        return pLng;
    }

    public void setpLng(double pLng) {
        this.pLng = pLng;
    }

    public String getpIcon() {
        return pIcon;
    }

    public void setpIcon(String pIcon) {
        this.pIcon = pIcon;
    }
}
