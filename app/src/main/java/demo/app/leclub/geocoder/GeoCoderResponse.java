package demo.app.leclub.geocoder;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/10/2016.
 */
public class GeoCoderResponse {
    @SerializedName("address_components")
    public List<AddressComponent> addressComponents = new ArrayList<>();
    @SerializedName("formatted_address")
    public String formattedAddress;
    @SerializedName("geometry")
    public Geometry geometry;
    @SerializedName("partial_match")
    public Boolean partialMatch;
    @SerializedName("place_id")
    public String placeId;
    @SerializedName("types")
    public List<String> types = new ArrayList<>();
}
