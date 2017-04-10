package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.EventData;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.api.request.AgendaRequest;
import demo.app.leclub.api.response.AgendaResponse;
import demo.app.leclub.bean.AgendaBean;
import demo.app.leclub.constants.HeaderOption;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.util.FragmentUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AgendaFragment extends HeaderFragment implements DatePickerController {
    @BindView(R.id.dayPickerView)
    DayPickerView dayPickerView;
    List<AgendaBean> agendaBeanList;
    ArrayList<EventData> eventDataArrayList;
    HashMap<String, AgendaBean> agendaBeanHashMap;
    Calendar currentCalendar;
    SimpleMonthAdapter.CalendarDay calendarDay;
    boolean disableSelectDayEvent = false;

    public static AgendaFragment newInstance() {
        AgendaFragment fragment = new AgendaFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_agenda;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        disableSelectDayEvent = true;
        currentCalendar = Calendar.getInstance();
        dayPickerView.setController(this);
    }

    @Override
    protected void initData() {
        if (agendaBeanList == null) {
            getAgendaData();
        } else {
            if (calendarDay != null) {
                dayPickerView.setSelectedDay(calendarDay);
            }
            setUpAgenda(agendaBeanList);
        }
        disableSelectDayEvent = false;
    }

    private void getAgendaData() {
        AgendaRequest agendaRequest = new AgendaRequest();
        agendaRequest.setRequestCallBack(new ApiObjectCallBack<AgendaResponse>() {
            @Override
            public void onSuccess(AgendaResponse data) {
                initialResponseHandled();
                setUpAgenda(data.agenda);
            }

            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        agendaRequest.execute();
    }

    private void setUpAgenda(List<AgendaBean> inAgendaBeanList) {
        this.agendaBeanList = inAgendaBeanList;
        eventDataArrayList = new ArrayList<>();
        agendaBeanHashMap = new HashMap<>();
        for (AgendaBean item : agendaBeanList) {
            String[] date = getDateArray(item);
            if (date.length > 2) {
                int day = Integer.parseInt(date[2]);
                int month = Integer.parseInt(date[1]) - 1;
                int year = Integer.parseInt(date[0]);
                eventDataArrayList.add(new EventData(true, day, month, year));
                agendaBeanHashMap.put(getKeyString(day, month, year), item);
            }
            dayPickerView.setEventDatas(eventDataArrayList);
        }

    }

    private String getKeyString(int mDay, int mMonth, int mYear) {
        return mDay + "-" + mMonth + "-" + mYear;
    }

    private String[] getDateArray(AgendaBean agendaBean) {
        return agendaBean.date.split("-");
    }

    @Override
    public String getScreenTitle() {
        return "Agenda";
    }

    @Override
    protected int getLeftIcon() {
        return HeaderOption.LEFT_MENU;
    }

    @Override
    protected int getRightIcon() {
        return HeaderOption.RIGHT_TODAY;
    }

    @Override
    protected boolean isStartWithLoading() {
        return agendaBeanList == null;
    }

    @Override
    public int getMaxYear() {
        return currentCalendar.get(Calendar.YEAR) + 30;
    }

    @Override
    public int getMinYear() {
        return currentCalendar.get(Calendar.YEAR) - 10;
    }

    @Override
    public int getCurrentYear() {
        return currentCalendar.get(Calendar.YEAR);
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        if (agendaBeanHashMap != null && !disableSelectDayEvent) {
            AgendaBean agendaBean = agendaBeanHashMap.get(getKeyString(day, month, year));
            if (agendaBean != null) {
                calendarDay = new SimpleMonthAdapter.CalendarDay(year, month, day);
                FragmentUtil.pushFragment(getActivity(), AgendaDetailFragment.newInstance(agendaBean), null);
            }
        }
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

    }

    @Override
    public void onFragmentDataHandle(Bundle bundle) {
        super.onFragmentDataHandle(bundle);
        if (bundle != null && bundle.containsKey(HeaderOption.HEADER_CONTROL)) {
            int command = bundle.getInt(HeaderOption.HEADER_CONTROL);
            if (command == HeaderOption.RIGHT_TODAY) {
                dayPickerView.scrollToMonth(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH));
            }
        }
    }
}
