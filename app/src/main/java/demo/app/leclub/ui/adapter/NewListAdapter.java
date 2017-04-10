package demo.app.leclub.ui.adapter;

import android.view.ViewGroup;

import java.util.List;

import demo.app.leclub.bean.NewBean;
import demo.app.leclub.ui.adapter.viewholder.NewListViewHolder;
import vn.app.base.adapter.AdapterWithItemClick;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 16/02/17.
 */

public class NewListAdapter extends AdapterWithItemClick<NewListViewHolder> {
    public List<NewBean> newBeanList;

    public NewListAdapter(List<NewBean> newBeanList) {
        this.newBeanList = newBeanList;
    }

    @Override
    public NewListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewListViewHolder(UiUtil.inflate(parent, NewListViewHolder.LAYOUT_ID, false));
    }

    @Override
    public int getItemCount() {
        return newBeanList.size();
    }

    @Override
    public void onBindViewHolder(NewListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(newBeanList.get(position));
    }
}
