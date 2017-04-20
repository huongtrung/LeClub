package demo.app.leclub.ui.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.api.request.EventConfirmRequest;
import demo.app.leclub.api.request.EventRequest;
import demo.app.leclub.api.response.EventResponse;
import demo.app.leclub.bean.AgendaBean;
import demo.app.leclub.bean.EventBean;
import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.constants.HeaderOption;
import demo.app.leclub.customview.ScrollMapView;
import demo.app.leclub.geocoder.GecodingListener;
import demo.app.leclub.geocoder.GeoLatLong;
import demo.app.leclub.geocoder.ManualGeoCodingRequest;
import demo.app.leclub.manager.EventManager;
import demo.app.leclub.manager.UserManager;
import demo.app.leclub.util.CalendarHelper;
import vn.app.base.api.response.BaseResponse;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.DialogUtil;
import vn.app.base.util.IntentUtil;
import vn.app.base.util.StringUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaDetailFragment extends HeaderFragment implements OnMapReadyCallback {
    private static final int EVENT_NO_DECIDE = 0;
    private static final int EVENT_NO_JOIN = 1;
    private static final int EVENT_JOIN = 2;

    @BindView(R.id.sv_detail)
    ScrollView svDetail;
    @BindView(R.id.iv_agenda_detail)
    ImageView ivPhoto;
    @BindView(R.id.tv_agenda_detail_join)
    TextView tvJoin;
    @BindView(R.id.tv_agenda_detail_cannot_join)
    TextView tvCannotJoin;
    @BindView(R.id.tv_agenda_detail_date)
    TextView tvDate;
    @BindView(R.id.tv_agenda_detail_title)
    TextView tvTitle;
    @BindView(R.id.tv_agenda_detail_address)
    TextView tvAddress;
    @BindView(R.id.tv_agenda_detail_web)
    TextView tvWeb;
    @BindView(R.id.tv_agenda_detail_content)
    TextView tvContent;
    @BindView(R.id.agenda_detail_map)
    ScrollMapView scrollMapView;
    GoogleMap mMap;

    public static final long ONE_DAY_IN_MILIS = 1000 * 60 * 60 * 24;
    Calendar eventCalendar;
    EventBean eventBean;
    UserProfileBean userProfileBean;
    private int eventStatus = EVENT_NO_DECIDE;
    EventManager eventManager;
    LatLng eventPosition;
    Marker marker;

    public static final String AGENDA_DATA = "AGENDA_DATA";
    private AgendaBean agendaBean;

    public static AgendaDetailFragment newInstance(AgendaBean agendaBean) {
        AgendaDetailFragment fragment = new AgendaDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AGENDA_DATA, agendaBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_agenda_detail;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleMapScroll(savedInstanceState);
    }

    @Override
    protected void getArgument(Bundle bundle) {
        agendaBean = bundle.getParcelable(AGENDA_DATA);
    }

    @Override
    protected void initData() {
        if (agendaBean != null) {
            ImageLoader.loadImage(getContext(), R.drawable.loading_list_image_220, agendaBean.image, ivPhoto);
            StringUtil.displayText(getStringDate(agendaBean.date), tvDate);
            StringUtil.displayText(agendaBean.type, tvTitle);
            StringUtil.displayTextHtml(agendaBean.adresse, tvAddress);
            StringUtil.displayText(agendaBean.lien, tvWeb);
            StringUtil.displayTextHtml(agendaBean.texte, tvContent);
            userProfileBean = UserManager.getCurrentUser();
            eventManager = new EventManager();
            if (eventBean == null) {
                getEventData();
            } else {
                updateEventConfirmStatus();
            }
        }
    }

    private String getStringDate(String dateTime) {
        eventCalendar = Calendar.getInstance();
        String[] returnDateTime = dateTime.split("-");
        if (returnDateTime.length > 2) {
            eventCalendar.set(Integer.parseInt(returnDateTime[0]), Integer.parseInt(returnDateTime[1]) - 1, Integer.parseInt(returnDateTime[2]));
            return returnDateTime[2] + "/" + returnDateTime[1] + "\n\n" + returnDateTime[0];
        } else {
            return "";
        }
    }

    private void getEventData() {
        showCoverNetworkLoading();
        EventRequest eventRequest = new EventRequest(userProfileBean.id, agendaBean.id);
        eventRequest.setRequestCallBack(new ApiObjectCallBack<EventResponse>() {
            @Override
            public void onSuccess(EventResponse data) {
                hideCoverNetworkLoading();
                if (data != null && data.event != null) {
                    eventBean = data.event;
                    eventStatus = Integer.parseInt(data.event.eventStatus);
                    updateEventConfirmStatus();
                }
            }

            @Override
            public void onFail(int failCode, String message) {
                hideCoverNetworkLoading();
            }
        });
        eventRequest.execute();
    }

    private void updateEventConfirmStatus() {
        if (eventStatus == EVENT_NO_JOIN) {
            tvJoin.setTextColor(ContextCompat.getColor(getActivity(), R.color.txt_gray));
            tvCannotJoin.setTextColor(ContextCompat.getColor(getActivity(), R.color.avantages_link_pink));
        } else if (eventStatus == EVENT_JOIN) {
            tvJoin.setTextColor(ContextCompat.getColor(getActivity(), R.color.avantages_link_pink));
            tvCannotJoin.setTextColor(ContextCompat.getColor(getActivity(), R.color.txt_gray));
        }
    }

    private void sendEventEmail(final int status) {
        if (status == eventStatus) {
            return;
        }
        showCoverNetworkLoading();
        EventConfirmRequest eventConfirmRequest = new EventConfirmRequest(userProfileBean, eventBean.id, status, eventBean.date);
        eventConfirmRequest.setRequestCallBack(new ApiObjectCallBack<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse data) {
                hideCoverNetworkLoading();
                if (status == EVENT_JOIN) {
                    long calendarEventId = CalendarHelper.makeNewCalendarEntry(getActivity(), agendaBean.type, "", agendaBean.adresse, eventCalendar.getTimeInMillis(), eventCalendar.getTimeInMillis() + ONE_DAY_IN_MILIS, true, true, 1, 3);
                    eventManager.saveOneEvent(Integer.valueOf(agendaBean.id), calendarEventId);
                } else if (status == EVENT_NO_JOIN) {
                    long calendarEventId = eventManager.getEventById(Integer.valueOf(agendaBean.id));
                    if (calendarEventId >= 0) {
                        CalendarHelper.deleteCalendarEntry(getActivity(), calendarEventId);
                    }
                }
                eventStatus = status;
                updateEventConfirmStatus();
                DialogUtil.showOkBtnDialog(getActivity(), getString(R.string.app_name), "success");
            }

            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        eventConfirmRequest.execute();
    }

    @OnClick(R.id.tv_agenda_detail_cannot_join)
    public void noJoinEvent() {
        sendEventEmail(EVENT_NO_JOIN);
    }

    @OnClick(R.id.tv_agenda_detail_join)
    public void joinEvent() {
        sendEventEmail(EVENT_JOIN);
    }

    @Override
    protected int getLeftIcon() {
        return HeaderOption.LEFT_BACK;
    }

    @Override
    protected int getRightIcon() {
        return HeaderOption.RIGHT_SEARCH;
    }

    private void handleMapScroll(Bundle stateInstanceState) {
        scrollMapView.onCreate(stateInstanceState);
        scrollMapView.setViewParent(svDetail);
        scrollMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        scrollMapView.onResume();
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                gotoMapAddress();
            }
        });
        setLocationAddress(agendaBean.adresse);
    }

    private void gotoMapAddress() {
        Uri mapUri;
        if (eventPosition !=null)
//            mapUri = Uri.parse("geo:"+eventPosition.latitude+","+eventPosition.longitude+"?q="+eventPosition.latitude+","+eventPosition.longitude+"&z=17");
            mapUri = Uri.parse("geo:" + eventPosition.latitude + "," + eventPosition.longitude + "?q=" +eventPosition.latitude + "," + eventPosition.longitude + "&z=15");
        else
            mapUri = Uri.parse("geo:0,0?z=17&q="+getDecodeAddress(eventBean.adresse));
        IntentUtil.openMap(getActivity(),mapUri);
    }

    private String getDecodeAddress(String address) {
        try {
            return URLDecoder.decode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return address.replace(" ", "+");
        }
    }

    private void setLocationAddress(final String address) {
        ManualGeoCodingRequest manualGeoCodingRequest = new ManualGeoCodingRequest(address);
        manualGeoCodingRequest.setGecodingListener(new GecodingListener() {
            @Override
            public void onSuccess(GeoLatLong latLong) {
                eventPosition = new LatLng(latLong.lat, latLong.lng);
                if (marker == null) {
                    marker = mMap.addMarker(new MarkerOptions().position(eventPosition).title(address));
                } else {
                    marker.setPosition(eventPosition);
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventPosition, 15f));
            }

            @Override
            public void onFail(String message) {

            }
        });
        manualGeoCodingRequest.execute();
    }
}
