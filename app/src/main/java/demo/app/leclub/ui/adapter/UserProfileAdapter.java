package demo.app.leclub.ui.adapter;

import android.view.ViewGroup;

import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.bean.UserProfileItemBean;
import demo.app.leclub.callback.OnUserEdit;
import demo.app.leclub.ui.adapter.viewholder.UserProfileHeaderViewHolder;
import demo.app.leclub.ui.adapter.viewholder.UserProfileItemViewHolder;
import vn.app.base.adapter.HeaderAdapterWithItemClick;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 23/02/17.
 */

public class UserProfileAdapter extends HeaderAdapterWithItemClick<OnClickViewHolder, UserProfileBean, UserProfileItemBean, String> {
    OnUserEdit onUserEdit;

    public void setOnUserEdit(OnUserEdit onUserEdit) {
        this.onUserEdit = onUserEdit;
    }

    @Override
    protected OnClickViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return new UserProfileHeaderViewHolder(UiUtil.inflate(parent, UserProfileHeaderViewHolder.LAYOUT_ID, false));
    }

    @Override
    protected OnClickViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new UserProfileItemViewHolder(UiUtil.inflate(parent, UserProfileItemViewHolder.LAYOUT_ID, false));
    }

    @Override
    protected void onBindHeaderViewHolder(OnClickViewHolder holder, int position) {
        super.onBindHeaderViewHolder(holder, position);
        ((UserProfileHeaderViewHolder) holder).bind(getHeader());
        ((UserProfileHeaderViewHolder) holder).setOnUserEdit(onUserEdit);
    }

    @Override
    protected void onBindItemViewHolder(OnClickViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        UserProfileItemBean userProfileItemBean = getItem(position);
        ((UserProfileItemViewHolder) holder).bind(userProfileItemBean);
        if (userProfileItemBean.isEditable) {
            ((UserProfileItemViewHolder) holder).setOnUserEdit(onUserEdit);
        }
    }
}
