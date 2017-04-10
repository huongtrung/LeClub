package demo.app.leclub.callback;

import demo.app.leclub.bean.MemberBean;

/**
 * Created by HuongTrung on 17/02/17.
 */

public interface OnMemberInteract {
    void onCallPhone(MemberBean memberBean);

    void onSendMail(MemberBean memberBean);
}
