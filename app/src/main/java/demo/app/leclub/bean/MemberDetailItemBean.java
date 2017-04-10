package demo.app.leclub.bean;

/**
 * Created by HuongTrung on 21/02/17.
 */

public class MemberDetailItemBean {
    public String label;
    public String value;
    public boolean isClickable;

    public MemberDetailItemBean(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public MemberDetailItemBean(String label, String value, boolean isClickable) {
        this.label = label;
        this.value = value;
        this.isClickable = isClickable;
    }
}
