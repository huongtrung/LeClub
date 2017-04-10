package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.bean.AdvantagesBean;
import demo.app.leclub.util.AppStringUtil;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.IntentUtil;
import vn.app.base.util.StringUtil;
import vn.app.base.util.UiUtil;

public class AdvantagesDetailFragment extends HeaderFragment {
    AdvantagesBean advantagesBean;
    private static final String KEY_ADVANTAGES_DETAIL = "KEY_ADVANTAGES_DETAIL";
    @BindView(R.id.iv_advantages_photo)
    ImageView ivAdvantagesPhoto;
    @BindView(R.id.tv_advantages_title)
    TextView tvAdvantagesTitle;
    @BindView(R.id.tv_advantages_date)
    TextView tvAdvantagesDate;
    @BindView(R.id.tv_advantages_content)
    TextView tvAdvantagesContent;
    @BindView(R.id.ll_advantages_link)
    LinearLayout llAdvantagesLink;
    @BindView(R.id.tv_advantages_link)
    TextView tvAdvantagesLink;

    public static AdvantagesDetailFragment newInstance(AdvantagesBean advantagesBean) {
        AdvantagesDetailFragment fragment = new AdvantagesDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ADVANTAGES_DETAIL, advantagesBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_advantages_detail;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        advantagesBean = bundle.getParcelable(KEY_ADVANTAGES_DETAIL);
    }

    @Override
    protected void initData() {
        if (advantagesBean != null) {
            ImageLoader.loadImage(getContext(), R.drawable.loading_list_image_220, advantagesBean.image, ivAdvantagesPhoto);
            StringUtil.displayText(advantagesBean.titre, tvAdvantagesTitle);
            StringUtil.displayText(AppStringUtil.getDateString(advantagesBean.date), tvAdvantagesDate);
            StringUtil.displayTextHtml(advantagesBean.texte, tvAdvantagesContent);
            if (StringUtil.checkStringValid(advantagesBean.lien)) {
                UiUtil.showView(llAdvantagesLink);
                StringUtil.displayText(advantagesBean.lien, tvAdvantagesLink);
            } else {
                UiUtil.hideView(llAdvantagesLink, true);
            }
        }
    }

    @OnClick(R.id.ll_advantages_link)
    void openWebPage() {
        if (StringUtil.checkStringValid(advantagesBean.lien)) {
            IntentUtil.openWebPage(getActivity(), advantagesBean.lien);
        }
    }
}
