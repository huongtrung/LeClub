package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.bean.NewBean;
import demo.app.leclub.util.AppStringUtil;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 16/02/17.
 */

public class NewListViewHolder extends OnClickViewHolder {

    public static final int LAYOUT_ID = R.layout.item_actualites;

    @BindView(R.id.iv_actualites)
    ImageView ivNewList;
    @BindView(R.id.tv_title_actualites)
    TextView tvTitleNewList;
    @BindView(R.id.tv_description_actualites)
    TextView tvDescriptionNewList;
    @BindView(R.id.tv_time_actualites)
    TextView tvTimeNewList;
    @BindView(R.id.probar_actualites)
    ProgressBar pbNewList;

    public NewListViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(NewBean newBean) {
        ImageLoader.loadImage(itemView.getContext(), R.drawable.loading_list_image_220, newBean.image, ivNewList);
        StringUtil.displayText(newBean.titre, tvTitleNewList);
        StringUtil.displayText(newBean.shortDescription, tvDescriptionNewList);
        StringUtil.displayText(AppStringUtil.getDateString(newBean.date), tvTimeNewList);
    }
}
