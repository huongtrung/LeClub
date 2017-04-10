package demo.app.leclub.ui.adapter;

import android.view.ViewGroup;

import java.util.List;

import demo.app.leclub.bean.AdvantagesBean;
import demo.app.leclub.callback.OpenWebPage;
import demo.app.leclub.ui.adapter.viewholder.AdvantagesListViewHolder;
import vn.app.base.adapter.AdapterWithItemClick;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 20/02/17.
 */

public class AdvantagesListAdapter extends AdapterWithItemClick<AdvantagesListViewHolder> {
    List<AdvantagesBean> advantagesBeanList;
    OpenWebPage openWebPage;

    public AdvantagesListAdapter(List<AdvantagesBean> advantagesBeanList) {
        this.advantagesBeanList = advantagesBeanList;
    }

    public void setOpenWebPage(OpenWebPage openWebPage) {
        this.openWebPage = openWebPage;
    }

    @Override
    public AdvantagesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AdvantagesListViewHolder(UiUtil.inflate(parent, AdvantagesListViewHolder.LAYOUT_ID, false));
    }

    @Override
    public void onBindViewHolder(AdvantagesListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(advantagesBeanList.get(position),openWebPage);
    }


    @Override
    public int getItemCount() {
        return advantagesBeanList.size();
    }
}
