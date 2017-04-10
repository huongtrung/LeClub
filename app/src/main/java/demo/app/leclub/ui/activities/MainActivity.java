package demo.app.leclub.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.bean.HeaderControlBean;
import demo.app.leclub.constants.HeaderOption;
import demo.app.leclub.ui.fragment.AdFragment;
import demo.app.leclub.ui.fragment.LoginFragment;
import demo.app.leclub.ui.fragment.MenuFragment;
import demo.app.leclub.ui.fragment.SearchFragment;
import vn.app.base.activity.CommonActivity;
import vn.app.base.util.FragmentUtil;
import vn.app.base.util.ImagePickerUtil;
import vn.app.base.util.StringUtil;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 06/02/17.
 */

public class MainActivity extends CommonActivity {
    @BindView(R.id.toolbar)
    RelativeLayout rlHeader;

    @BindView(R.id.toolbar_spacer)
    View vToolbarSpacer;

    @BindView(R.id.headerMenu)
    ImageView ivMenu;

    @BindView(R.id.headerBack)
    ImageView ivBack;

    @BindView(R.id.headerSearch)
    ImageView ivSearch;

    @BindView(R.id.headerToday)
    TextView tvToday;

    @BindView(R.id.headerTitle)
    TextView tvTitle;

    String userPhotoPath;
    boolean isAppBackground = false;
    boolean isStartAd = false;
    ImagePickerUtil imagePickerUtil = new ImagePickerUtil();

    @Override
    protected String getNoConnectionMessage() {
        return getString(R.string.no_connect_internet);
    }

    @Override
    protected String getErrorAPIMessage() {
        return getString(R.string.no_connect_internet);
    }

    @Override
    protected String getTimeOutMessage() {
        return getString(R.string.dialog_server_timeout);
    }

    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setUpInitScreen(LoginFragment.newInstance(), null);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        imagePickerUtil.handleResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_OK) {
            if (requestCode == ImagePickerUtil.PICTURE_PICKER_REQUEST_CODE) {
                imagePickerUtil.createImageFile(this);

            }
        }
    }

    @Override
    public void onCommonDataHandle(Bundle bundle) {
        if (bundle != null) {
            //TODO handle common data
        }
    }

    @Override
    public void onCommonUIHandle(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey(HeaderOption.HEADER_CONTROL)) {
            HeaderControlBean headerControlBean = bundle.getParcelable(HeaderOption.HEADER_CONTROL);
            if (headerControlBean != null) {
                handleHeaderUI(headerControlBean);
            }
        }
    }

    private void handleHeaderUI(HeaderControlBean headerControlBean) {
        if (headerControlBean.headerOptions != null && headerControlBean.headerOptions.length > 0) {
            if (headerControlBean.headerOptions[0] == HeaderOption.SHOW_HEADER) {
                rlHeader.setBackgroundColor(ContextCompat.getColor(this, R.color.ocean_blue));
                UiUtil.showView(rlHeader);
                UiUtil.showView(vToolbarSpacer);
            } else if (headerControlBean.headerOptions[0] == HeaderOption.HIDE_HEADER) {
                UiUtil.hideView(rlHeader, true);
                UiUtil.hideView(vToolbarSpacer, true);
            } else if (headerControlBean.headerOptions[0] == HeaderOption.SHOW_HEADER_TRANSPARENT) {
                UiUtil.showView(rlHeader);
                UiUtil.hideView(vToolbarSpacer, true);
                rlHeader.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
            }
            if (headerControlBean.headerOptions.length > 1 && headerControlBean.headerOptions[1] > 0) {
                handleLeftIcon(headerControlBean.headerOptions[1]);
                if (headerControlBean.headerOptions.length > 2 && headerControlBean.headerOptions[2] > 0) {
                    handleRightIcon(headerControlBean.headerOptions[2]);
                } else {
                    showAndHideIconRight(null);
                }
            } else {
                showAndHideIconLeft(ivMenu);
                showAndHideIconRight(ivSearch);
            }
        }
        if (StringUtil.checkStringValid(headerControlBean.title)) {
            StringUtil.displayText(headerControlBean.title, tvTitle);
        }
    }

    private void handleLeftIcon(int value) {
        switch (value) {
            case HeaderOption.LEFT_NO_OPTION:
                showAndHideIconLeft(null);
                break;
            case HeaderOption.LEFT_MENU:
                showAndHideIconLeft(ivMenu);
                break;
            case HeaderOption.LEFT_BACK:
                showAndHideIconLeft(ivBack);
                break;
        }
    }

    private void handleRightIcon(int value) {
        switch (value) {
            case HeaderOption.RIGHT_NO_OPTION:
                showAndHideIconRight(null);
                break;
            case HeaderOption.RIGHT_SEARCH:
                showAndHideIconRight(ivSearch);
                break;
            case HeaderOption.RIGHT_TODAY:
                showAndHideIconRight(tvToday);
                break;
        }
    }

    private void showAndHideIconLeft(View target) {
        showOrHide(ivMenu, target);
        showOrHide(ivBack, target);
    }

    private void showAndHideIconRight(View target) {
        showOrHide(ivSearch, target);
        showOrHide(tvToday, target);
    }

    protected void showOrHide(View subject, View target) {
        subject.setVisibility(subject == target ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.headerMenu)
    void clickHeaderMenu() {
        popEntireFragmentBackStack();
        FragmentUtil.pushFragment(this, MenuFragment.newInstance(), null);
    }

    @OnClick(R.id.headerBack)
    void clickHeaderBack() {
        FragmentUtil.popBackStack(this);
    }

    @OnClick(R.id.headerSearch)
    void clickHeaderSearch() {
        FragmentUtil.pushFragment(this, SearchFragment.newInstance(null), null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isAppBackground = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isAppBackground && !isStartAd)
            showAd();
        else {
            isAppBackground = false;
            isStartAd = false;
        }
    }

    private void showAd() {
        Fragment fragment = FragmentUtil.getCurrentFragmentByTag(this, AdFragment.TAG);
        if (fragment == null || !fragment.isVisible()) {
            Intent intent = new Intent(this, AdActivity.class);
            startActivity(intent);
            isStartAd = true;
        }
    }
}
