package demo.app.leclub.geocoder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created on 9/10/2016.
 */
public class Geometry {
    @SerializedName("bounds")
    @Expose
    public Bound bounds;
    @SerializedName("location")
    @Expose
    public GeoLatLong location;
    @SerializedName("location_type")
    @Expose
    public String locationType;
    @SerializedName("viewport")
    @Expose
    public Bound viewport;
}
