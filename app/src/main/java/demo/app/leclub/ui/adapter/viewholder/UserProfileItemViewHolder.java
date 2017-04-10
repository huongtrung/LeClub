package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.bean.UserProfileItemBean;
import demo.app.leclub.callback.OnUserEdit;
import demo.app.leclub.util.AppStringUtil;
import me.grantland.widget.AutofitTextView;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.util.StringUtil;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 23/02/17.
 */

public class UserProfileItemViewHolder extends OnClickViewHolder {
    public static final int LAYOUT_ID = R.layout.item_user_profile_list;

    OnUserEdit onUserEdit;
    UserProfileItemBean userProfileItemBean;
    @BindView(R.id.tv_title_item_user)
    TextView tvItemUserTitle;
    @BindView(R.id.tv_user_profile_label)
    AutofitTextView tvUserLabel;
    @BindView(R.id.tv_user_profile_value)
    TextView tvUserValue;
    @BindView(R.id.ll_item_content)
    LinearLayout llItemContent;
    @BindView(R.id.iv_user_profile_edit)
    ImageView ivEdit;

    public void setOnUserEdit(OnUserEdit onUserEdit) {
        this.onUserEdit = onUserEdit;
    }

    public UserProfileItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(UserProfileItemBean itemBean) {
        this.userProfileItemBean = itemBean;
        if (userProfileItemBean.isHeader) {
            setOnRecyclerViewItemClick(null);
            UiUtil.showView(tvItemUserTitle);
            UiUtil.hideView(llItemContent, true);
            StringUtil.displayText(userProfileItemBean.label, tvItemUserTitle);
        } else {
            UiUtil.showView(llItemContent);
            UiUtil.hideView(tvItemUserTitle, true);
            StringUtil.displayText(userProfileItemBean.label, tvUserLabel);
            if (userProfileItemBean.value.startsWith(AppStringUtil.HTTTP_PREFIX)) {
                StringUtil.displayText(AppStringUtil.ellipsize(userProfileItemBean.value, AppStringUtil.NUMBER_CHARACTER_AFTER_CUT), tvUserValue);
            } else {
                StringUtil.displayText(userProfileItemBean.value, tvUserValue);
            }
            if (userProfileItemBean.isEditable) {
                ivEdit.setClickable(true);
                UiUtil.showView(ivEdit);
            } else {
                UiUtil.hideView(ivEdit, false);
            }
        }
    }

    @OnClick(R.id.iv_user_profile_edit)
    void edit() {
        if (onUserEdit != null) {
            onUserEdit.onEdit(getAdapterPosition(), userProfileItemBean);
        }
    }
}
