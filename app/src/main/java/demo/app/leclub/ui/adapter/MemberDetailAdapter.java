package demo.app.leclub.ui.adapter;

import android.view.ViewGroup;

import demo.app.leclub.bean.MemberBean;
import demo.app.leclub.bean.MemberDetailItemBean;
import demo.app.leclub.callback.OnMemberInteract;
import demo.app.leclub.ui.adapter.viewholder.MemberDetailHeaderViewHolder;
import demo.app.leclub.ui.adapter.viewholder.MemberDetailItemViewHolder;
import vn.app.base.adapter.HeaderAdapterWithItemClick;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 21/02/17.
 */

public class MemberDetailAdapter extends HeaderAdapterWithItemClick<OnClickViewHolder, MemberBean, MemberDetailItemBean, String> {
    OnMemberInteract onMemberInteract;

    public void setOnMemberInteract(OnMemberInteract onMemberInteract) {
        this.onMemberInteract = onMemberInteract;
    }

    @Override
    protected OnClickViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return new MemberDetailHeaderViewHolder(UiUtil.inflate(parent, MemberDetailHeaderViewHolder.LAYOUT_ID, false));
    }

    @Override
    protected OnClickViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new MemberDetailItemViewHolder(UiUtil.inflate(parent, MemberDetailItemViewHolder.LAYOUT_ID, false));
    }

    @Override
    protected void onBindHeaderViewHolder(OnClickViewHolder holder, int position) {
        super.onBindHeaderViewHolder(holder, position);
        ((MemberDetailHeaderViewHolder) holder).bind(getHeader(), onMemberInteract);
    }

    @Override
    protected void onBindItemViewHolder(OnClickViewHolder holder, int position) {
        super.onBindItemViewHolder(holder, position);
        MemberDetailItemBean itemBean = getItem(position);
        ((MemberDetailItemViewHolder) holder).bind(itemBean);
    }
}
