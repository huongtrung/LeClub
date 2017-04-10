package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import demo.app.leclub.R;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 22/02/17.
 */

public class StickHeaderSearchViewHolder extends OnClickViewHolder {
    public static final int LAYOUT_ID=R.layout.item_search_header;

    @BindView(R.id.tv_search_result_header)
    TextView tvSearchHeader;

    public StickHeaderSearchViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(String headerTitle) {
        StringUtil.displayText(headerTitle, tvSearchHeader);
    }
}
