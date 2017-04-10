package demo.app.leclub.ui.fragment;

import android.os.Bundle;
import android.view.View;

import demo.app.leclub.bean.HeaderControlBean;
import demo.app.leclub.constants.HeaderOption;
import vn.app.base.fragment.CommonFragment;

/**
 * Created by HuongTrung on 08/02/17.
 */

public abstract class HeaderFragment extends CommonFragment {
    @Override
    protected void initView(View root) {
        if (commonListener != null) {
            commonListener.onCommonUIHandle(showHeaderBundle());
        }
    }

    private Bundle showHeaderBundle() {
        Bundle bundle = new Bundle();
        HeaderControlBean headerControlBean = new HeaderControlBean(getScreenTitle());
        headerControlBean.setHeaderOption(isHeaderTransparent() ? HeaderOption.SHOW_HEADER_TRANSPARENT : HeaderOption.SHOW_HEADER, getLeftIcon(), getRightIcon());
        bundle.putParcelable(HeaderOption.HEADER_CONTROL, headerControlBean);
        return bundle;
    }

    protected int getLeftIcon() {
        return 0;
    }

    protected int getRightIcon() {
        return 0;
    }

    protected boolean isHeaderTransparent() {
        return false;
    }
}
