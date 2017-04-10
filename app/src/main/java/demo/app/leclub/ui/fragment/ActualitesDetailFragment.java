package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.bean.NewBean;
import demo.app.leclub.constants.HeaderOption;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.IntentUtil;
import vn.app.base.util.StringUtil;
import vn.app.base.util.UiUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActualitesDetailFragment extends HeaderFragment {
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_actualites_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.tv_actualites_detail_date)
    TextView tvDetaiDate;
    @BindView(R.id.tv_actualites_detail_content)
    TextView tvDetailContent;
    @BindView(R.id.tv_actualites_detail_link)
    TextView tvDetailLink;
    @BindView(R.id.ll_actualites_detail_link)
    LinearLayout llDetailLink;

    private NewBean newBean;
    public static final String NEWS_DATA = "NEWS_DATA";

    public static ActualitesDetailFragment newInstance(NewBean newBeanList) {
        ActualitesDetailFragment fragment = new ActualitesDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(NEWS_DATA, newBeanList);
        fragment.getArgument(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_actualites_detail;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        newBean = bundle.getParcelable(NEWS_DATA);
    }

    @Override
    protected void initData() {
        if (newBean != null) {
            ImageLoader.loadImage(getContext(), newBean.image, ivPhoto);
            StringUtil.displayText(newBean.titre, tvDetailTitle);
            StringUtil.displayText(newBean.date, tvDetaiDate);
            StringUtil.displayText(newBean.shortDescription, tvDetailContent);
            if (StringUtil.checkStringValid(newBean.lien))
                StringUtil.displayText(newBean.lien, tvDetailLink);
            else
                UiUtil.hideView(llDetailLink, true);
        }
    }

    @OnClick(R.id.ll_actualites_detail_link)
    void gotoWeb() {
        IntentUtil.openWebPage(getActivity(), newBean.lien);
    }

    @Override
    protected int getLeftIcon() {
        return HeaderOption.LEFT_BACK;
    }

    @Override
    protected int getRightIcon() {
        return HeaderOption.RIGHT_SEARCH;
    }
}
