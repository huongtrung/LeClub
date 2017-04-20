package demo.app.leclub.geocoder;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 9/10/2016.
 */
public class GeoLatLong {

    @SerializedName("lat")
    public double lat;
    @SerializedName("lng")
    public double lng;

    public GeoLatLong(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

}
