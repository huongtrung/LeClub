package demo.app.leclub.geocoder;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import vn.app.base.BaseApplication;
import vn.app.base.util.NetworkUtils;

/**
 * Created on 9/10/2016.
 */
public class ManualGeoCodingRequest {

    public static final String GEOCODE_URL = "http://maps.googleapis.com/maps/api/geocode/json?address=";

    public static final String SENSOR_OFF = "&sensor=false";

    String address;

    GecodingListener gecodingListener;

    public void setGecodingListener(GecodingListener gecodingListener) {
        this.gecodingListener = gecodingListener;
    }

    public ManualGeoCodingRequest(String address) {
        this.address = address;
    }

    public void execute() {
        if (!NetworkUtils.getInstance(BaseApplication.getInstance()).isNetworkConnected()) {
            if (gecodingListener != null) {
                gecodingListener.onFail("No connection!");
            }
            return;
        }
        String addressDecode;
        try {
            addressDecode = URLDecoder.decode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            addressDecode = address;
        }
        String url;
        try {
            url = GEOCODE_URL + URLEncoder.encode(addressDecode, "UTF-8") + SENSOR_OFF;
        } catch (UnsupportedEncodingException e) {
            url = GEOCODE_URL + addressDecode + SENSOR_OFF;
        }
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET
                , url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                handleSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (gecodingListener != null) {
                    gecodingListener.onFail(error.getMessage());
                }
            }
        });
        NetworkUtils.getInstance(BaseApplication.getInstance()).addToRequestQueue(jsonObjectRequest);
    }

    private void handleSuccess(String data) {
        try {
            JSONObject response = new JSONObject(data);
            double lng = ((JSONArray) response.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");
            double lat = ((JSONArray) response.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");
            if (gecodingListener != null) {
                gecodingListener.onSuccess(new GeoLatLong(lat, lng));
            }
        } catch (JSONException e) {
            if (gecodingListener != null) {
                gecodingListener.onFail(data);
            }
        }
    }
}
