package demo.app.leclub.ui.fragment;

import android.os.Bundle;
import android.view.View;

import demo.app.leclub.bean.HeaderControlBean;
import demo.app.leclub.constants.HeaderOption;
import vn.app.base.fragment.CommonFragment;

/**
 * Created by HuongTrung on 08/02/17.
 */

public abstract class NoHeaderFragment extends CommonFragment {

    @Override
    protected void initView(View root) {
        if (commonListener != null) {
            commonListener.onCommonUIHandle(hideHeaderBundle());
        }
    }

    private Bundle hideHeaderBundle() {
        Bundle bundle = new Bundle();
        HeaderControlBean headerControlBean = new HeaderControlBean(getScreenTitle(), HeaderOption.HIDE_HEADER);
        bundle.putParcelable(HeaderOption.HEADER_CONTROL, headerControlBean);
        return bundle;
    }
}
