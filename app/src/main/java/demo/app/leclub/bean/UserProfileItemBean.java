package demo.app.leclub.bean;

/**
 * Created by HuongTrung on 23/02/17.
 */

public class UserProfileItemBean {
    public boolean isHeader;
    public String label;
    public String value;
    public boolean isEditable;
    public boolean isClickable = false;
    public String paramKey;

    public UserProfileItemBean(String label) {
        this.isHeader = true;
        this.label = label;
    }

    public UserProfileItemBean(boolean isEditable, String label, String value, String paramKey) {
        this.isEditable = isEditable;
        this.label = label;
        this.paramKey = paramKey;
        this.value = value;
    }

    public UserProfileItemBean(boolean isClickable, boolean isEditable, String label, String value, String paramKey) {
        this.isEditable = isEditable;
        this.label = label;
        this.paramKey = paramKey;
        this.value = value;
        this.isClickable = isClickable;
    }
}
