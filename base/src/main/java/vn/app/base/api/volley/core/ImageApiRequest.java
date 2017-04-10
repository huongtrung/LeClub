package vn.app.base.api.volley.core;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;

import java.util.HashMap;

import vn.app.base.BaseApplication;
import vn.app.base.util.NetworkUtils;


public abstract class ImageApiRequest extends BaseApiRequest<Bitmap> {

    @Override
    protected void excecuteRequest() {
        if ((getMethod() == Request.Method.GET || getMethod() == Request.Method.DELETE) && getRequestParams() != null) {
            baseTypeRequest = new ImageRequest(getMethod(), createRequestUrl(), getListener(), getMaxWidth(), getMaxHeight(), getScaleType(), getBitmapConfig(), getErrorListener(), getRequestHeaders(), new HashMap<String, String>());
        } else {
            baseTypeRequest = new ImageRequest(getMethod(), createRequestUrl(), getListener(), getMaxWidth(), getMaxHeight(), getScaleType(), getBitmapConfig(), getErrorListener(), getRequestHeaders(), handleRequestParams());
        }
        baseTypeRequest.setGsonRequestHeaderOnResult(this);
        baseTypeRequest.setRetryPolicy(getDefaultRetryPolicy());
        NetworkUtils.getInstance(BaseApplication.getInstance()).addToRequestQueue(baseTypeRequest);
    }

    abstract public Bitmap.Config getBitmapConfig();

    abstract public int getMaxHeight();

    abstract public int getMaxWidth();

    abstract public ImageView.ScaleType getScaleType();

}
