package demo.app.leclub.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import demo.app.leclub.bean.LeaderBoardBean;
import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.constants.LeaderBoardType;
import demo.app.leclub.data.BrutComparator;
import demo.app.leclub.data.NetComparator;
import demo.app.leclub.ui.adapter.viewholder.LeaderBoadListViewHolder;
import demo.app.leclub.ui.adapter.viewholder.LeaderBoardHeaderViewHolder;
import vn.app.base.adapter.HeaderRecyclerViewAdapter;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 01/03/17.
 */

public class LeaderBoardAdapter extends HeaderRecyclerViewAdapter<RecyclerView.ViewHolder, UserProfileBean, LeaderBoardBean, String> {
    public LeaderBoardType leaderBoardType;
    OnRecyclerViewItemClick onRecyclerViewItemClick;

    public LeaderBoardAdapter(LeaderBoardType leaderBoardType) {
        this.leaderBoardType = leaderBoardType;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    @Override
    public void setItems(List<LeaderBoardBean> items) {
        super.setItems(items);
        sortByType();
    }

    private void sortByType() {
        switch (leaderBoardType) {
            case Brut:
                Collections.sort(items, new BrutComparator());
                break;
            case Net:
                Collections.sort(items, new NetComparator());
                break;
        }
    }

    public void changeSortType(LeaderBoardType leaderBoardType) {
        this.leaderBoardType = leaderBoardType;
        sortByType();
        notifyDataSetChanged();
    }


    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return new LeaderBoardHeaderViewHolder(UiUtil.inflate(parent, LeaderBoardHeaderViewHolder.LAYOUT_ID, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new LeaderBoadListViewHolder(UiUtil.inflate(parent, LeaderBoadListViewHolder.LAYOUT_ID, false));
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindHeaderViewHolder(holder, position);
        ((LeaderBoardHeaderViewHolder) holder).bind(getHeader(), this);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LeaderBoadListViewHolder) holder).bind(getItem(position), leaderBoardType);
        ((LeaderBoadListViewHolder) holder).setOnRecyclerViewItemClick(onRecyclerViewItemClick);
    }

}
