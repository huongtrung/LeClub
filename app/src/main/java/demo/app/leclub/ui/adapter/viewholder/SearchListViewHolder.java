package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import demo.app.leclub.R;
import demo.app.leclub.bean.AdvantagesBean;
import demo.app.leclub.bean.MemberBean;
import demo.app.leclub.bean.NewBean;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.StringUtil;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class SearchListViewHolder extends OnClickViewHolder {

    public static final int LAYOUT_ID = R.layout.item_search_result;

    @BindView(R.id.iv_search_result)
    ImageView ivSearchResult;
    @BindView(R.id.civ_image_result)
    CircleImageView civSearchResult;
    @BindView(R.id.tv_search_result_title)
    TextView tvTitle;
    @BindView(R.id.tv_search_result_description)
    TextView tvDescription;

    public SearchListViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(NewBean newBean) {
        UiUtil.showView(ivSearchResult);
        UiUtil.hideView(civSearchResult, true);
        ImageLoader.loadImage(itemView.getContext(), newBean.image, ivSearchResult);
        StringUtil.displayText(newBean.titre, tvTitle);
        StringUtil.displayText(newBean.shortDescription, tvDescription);
    }

    public void bind(MemberBean memberBean) {
        UiUtil.hideView(ivSearchResult, true);
        UiUtil.showView(civSearchResult);
        ImageLoader.loadImage(itemView.getContext(), memberBean.photo, civSearchResult);
        StringUtil.displayText(memberBean.getFullName(), tvTitle);
        StringUtil.displayText(memberBean.cProEmail, tvDescription);
    }

    public void bind(AdvantagesBean advantagesBean) {
        UiUtil.showView(ivSearchResult);
        UiUtil.hideView(civSearchResult, true);
        ImageLoader.loadImage(itemView.getContext(), advantagesBean.image, ivSearchResult);
        StringUtil.displayText(advantagesBean.titre, tvTitle);
        StringUtil.displayText(advantagesBean.shortDescription, tvDescription);
    }
}
