package demo.app.leclub.geocoder;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/10/2016.
 */
public class AddressComponent {
    @SerializedName("long_name")
    public String longName;
    @SerializedName("short_name")
    public String shortName;
    @SerializedName("types")
    public List<String> types = new ArrayList<String>();
}
