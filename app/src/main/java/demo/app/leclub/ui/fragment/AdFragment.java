package demo.app.leclub.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.api.request.PromotionRequest;
import demo.app.leclub.api.response.PromotionResponse;
import demo.app.leclub.bean.PromotionBean;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.FragmentUtil;
import vn.app.base.util.IntentUtil;

public class AdFragment extends NoHeaderFragment {
    @BindView(R.id.imgAd)
    ImageView ivAd;
    private static final String IS_APP_BACKGROUND = "IS_APP_BACKGROUND";
    public static final String TAG = "AdFragment";
    private Boolean isFromBackground;
    private PromotionBean promotionBean;

    public static AdFragment newInstance(boolean isAppBackground) {
        AdFragment fragment = new AdFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_APP_BACKGROUND, isAppBackground);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ad;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        isFromBackground = bundle.getBoolean(IS_APP_BACKGROUND);
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        ivAd.setClickable(false);
    }

    @Override
    protected void initData() {
        PromotionRequest promotionRequest = new PromotionRequest();
        promotionRequest.setRequestCallBack(new ApiObjectCallBack<PromotionResponse>() {
            @Override
            public void onSuccess(PromotionResponse data) {
                if (data.promotion != null)
                    displayAd(data.promotion);
                else
                    gotoNextScreen();
            }

            @Override
            public void onFail(int failCode, String message) {
                gotoNextScreen();
            }
        });
        promotionRequest.setSuppressCommonApiError(true);
        promotionRequest.execute();
    }

    private void gotoNextScreen() {
        if (isFromBackground) {
            FragmentUtil.popBackStack(getActivity());
        } else {
            FragmentUtil.replaceFragment(getActivity(), MenuFragment.newInstance(), null);
        }
    }

    private void displayAd(PromotionBean promotion) {
        if (promotion.image == null) {
            return;
        }
        ImageLoader.loadImage(getContext(), promotion.image, ivAd);
        ivAd.setClickable(true);
        this.promotionBean = promotion;
    }

    @OnClick(R.id.imgAd)
    public void openWebAd() {
        IntentUtil.openWebPage(getActivity(), promotionBean.url);
    }

    @OnClick(R.id.btnClose)
    public void closeAd() {
        gotoNextScreen();
    }
}
