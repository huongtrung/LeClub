package demo.app.leclub.ui.activities;

import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.api.request.PromotionRequest;
import demo.app.leclub.api.response.PromotionResponse;
import demo.app.leclub.bean.PromotionBean;
import vn.app.base.activity.BaseNoAPIActivity;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.IntentUtil;

public class AdActivity extends BaseNoAPIActivity {
    @BindView(R.id.layoutLoading)
    RelativeLayout rlLoading;
    @BindView(R.id.imgAd)
    ImageView ivAd;

    PromotionBean promotionBean;

    @Override
    public int setContentViewId() {
        return R.layout.activity_ad;
    }

    @Override
    public void initView() {
        ivAd.setClickable(false);
    }

    @Override
    public void initData() {
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
        finish();
    }

    private void displayAd(PromotionBean promotion) {
        if (ivAd == null)
            return;
        ImageLoader.loadImage(this, promotion.image, ivAd);
        ivAd.setClickable(true);
        this.promotionBean = promotion;
    }

    @OnClick(R.id.btnClose)
    public void closeAd() {
        gotoNextScreen();
    }

    @OnClick(R.id.imgAd)
    public void OpenWebAd() {
        IntentUtil.openWebPage(this, promotionBean.url);
    }
}
