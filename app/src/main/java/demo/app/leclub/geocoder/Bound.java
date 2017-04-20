package demo.app.leclub.geocoder;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 9/10/2016.
 */
public class Bound {
    @SerializedName("northeast")
    public GeoLatLong northeast;
    @SerializedName("southwest")
    public GeoLatLong southwest;

}
