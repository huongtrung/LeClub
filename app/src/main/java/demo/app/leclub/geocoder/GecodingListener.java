package demo.app.leclub.geocoder;

/**
 * Created on 9/10/2016.
 */
public interface GecodingListener {

    void onSuccess(GeoLatLong latLong);

    void onFail(String message);
}
