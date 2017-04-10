package demo.app.leclub.callback;

import demo.app.leclub.bean.UserProfileItemBean;

/**
 * Created by HuongTrung on 23/02/17.
 */

public interface OnUserEdit {
    void onEdit(int position, UserProfileItemBean userProfileItemBean);

    void onChangePhoto(int position);

    void onClickPhoto(String url);
}
