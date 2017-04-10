package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.bean.MemberDetailItemBean;
import demo.app.leclub.util.AppStringUtil;
import me.grantland.widget.AutofitTextView;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 21/02/17.
 */

public class MemberDetailItemViewHolder extends OnClickViewHolder {

    public static final int LAYOUT_ID = R.layout.item_member_detail;
    @BindView(R.id.tv_member_detail_label)
    AutofitTextView tvLabel;
    @BindView(R.id.tv_member_detail_value)
    TextView tvValue;

    public MemberDetailItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(MemberDetailItemBean memberDetailItemBean) {
        StringUtil.displayText(memberDetailItemBean.label, tvLabel);
        if (memberDetailItemBean.value.startsWith(AppStringUtil.HTTTP_PREFIX)) {
            StringUtil.displayText(AppStringUtil.ellipsize(memberDetailItemBean.value, AppStringUtil.NUMBER_CHARACTER_AFTER_CUT), tvValue);
        } else {
            StringUtil.displayText(memberDetailItemBean.value, tvValue);
        }
    }
}
