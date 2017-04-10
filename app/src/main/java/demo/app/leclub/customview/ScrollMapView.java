package demo.app.leclub.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

/**
 * Created by HuongTrung on 22/03/17.
 */

public class ScrollMapView extends MapView {
    private ViewParent mViewParent;

    public ScrollMapView(Context context) {
        super(context);
    }

    public ScrollMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ScrollMapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context, googleMapOptions);
    }

    public void setViewParent(@Nullable final ViewParent viewParent) {
        mViewParent = viewParent;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ((null == mViewParent))
                    getParent().requestDisallowInterceptTouchEvent(true);
                else
                    mViewParent.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                if ((null == mViewParent))
                    getParent().requestDisallowInterceptTouchEvent(false);
                else
                    mViewParent.requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
