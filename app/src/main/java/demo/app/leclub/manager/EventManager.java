package demo.app.leclub.manager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import vn.app.base.util.SharedPrefUtils;

/**
 * Created on 8/21/2016.
 */
public class EventManager {

    public static final String SAVED_EVENT = "SAVED_EVENT";
    public static final String EVENT_ID = "EVENT_ID";
    public static final String CALENDAR_EVENT_ID = "CALENDAR_EVENT_ID";

    HashMap<Integer, Long> eventMap;

    public EventManager() {
        this.eventMap = getEvent();
    }

    public long getEventById(Integer eventId) {
        if (this.eventMap.containsKey(eventId)) {
            return this.eventMap.get(eventId);
        } else {
            return -1;
        }
    }

    public void saveOneEvent(Integer eventId, Long calendarEventId) {
        this.eventMap.put(eventId, calendarEventId);
        saveEvent(this.eventMap);
    }

    public void deleteOneEvent(Integer eventId) {
        this.eventMap.remove(eventId);
        saveEvent(this.eventMap);
    }

    public void saveEvent(HashMap<Integer, Long> eventMap) {
        JSONArray arr = new JSONArray();
        for (Integer index : eventMap.keySet()) {
            JSONObject json = new JSONObject();
            try {
                json.put(EVENT_ID, index);
                json.put(CALENDAR_EVENT_ID, eventMap.get(index));
                arr.put(json);
            } catch (JSONException ignored) {
            }
        }
        SharedPrefUtils.putString(SAVED_EVENT, arr.toString());
    }

    public HashMap<Integer, Long> getEvent() {
        String data = SharedPrefUtils.getString(SAVED_EVENT, "");
        HashMap<Integer, Long> eventMap = new HashMap<>();
        try {
            JSONArray arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject json = arr.getJSONObject(i);
                eventMap.put(json.getInt(EVENT_ID), json.getLong(CALENDAR_EVENT_ID));
            }
        } catch (JSONException ignored) {
        }
        return eventMap;
    }

}
