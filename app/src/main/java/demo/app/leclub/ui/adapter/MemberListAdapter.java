package demo.app.leclub.ui.adapter;

import android.view.ViewGroup;

import java.util.List;

import demo.app.leclub.bean.MemberBean;
import demo.app.leclub.callback.OnMemberInteract;
import demo.app.leclub.ui.adapter.viewholder.MemberListViewHolder;
import vn.app.base.adapter.AdapterWithItemClick;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class MemberListAdapter extends AdapterWithItemClick<MemberListViewHolder> {
    public List<MemberBean> memberBeanList;
    OnMemberInteract onMemberInteract;

    public MemberListAdapter(List<MemberBean> memberBeanList, OnMemberInteract onMemberInteract) {
        this.memberBeanList = memberBeanList;
        this.onMemberInteract = onMemberInteract;
    }

    @Override
    public MemberListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberListViewHolder(UiUtil.inflate(parent, MemberListViewHolder.LAYOUT_ID, false));
    }

    @Override
    public void onBindViewHolder(MemberListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(memberBeanList.get(position), onMemberInteract);
    }

    @Override
    public int getItemCount() {
        return memberBeanList.size();
    }
}
