package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.bean.AdvantagesBean;
import demo.app.leclub.callback.OpenWebPage;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 20/02/17.
 */

public class AdvantagesListViewHolder extends OnClickViewHolder {
    public static final int LAYOUT_ID = R.layout.item_list_avantages;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_advantages_title)
    TextView tvTitle;
    @BindView(R.id.tv_advantages_link)
    TextView tvLink;
    OpenWebPage openWebPage;
    AdvantagesBean advantagesBean;

    public AdvantagesListViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(AdvantagesBean advantagesBean, OpenWebPage openWebPage) {
        this.advantagesBean = advantagesBean;
        this.openWebPage = openWebPage;
        ImageLoader.loadImage(itemView.getContext(), R.drawable.loading_list_image_113, advantagesBean.image, ivPhoto);
        StringUtil.displayText(advantagesBean.titre, tvTitle);
        if (advantagesBean.lien != null) {
            tvLink.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.tv_advantages_link)
    void openWeb() {
        if (openWebPage != null) {
            openWebPage.openWeb(advantagesBean.lien);
        }
    }
}
