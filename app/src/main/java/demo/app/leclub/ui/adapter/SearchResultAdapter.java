package demo.app.leclub.ui.adapter;

import android.view.ViewGroup;

import java.util.List;

import demo.app.leclub.bean.SearchResultBean;
import demo.app.leclub.ui.adapter.viewholder.SearchListViewHolder;
import demo.app.leclub.ui.adapter.viewholder.StickHeaderSearchViewHolder;
import vn.app.base.adapter.AdapterWithItemClick;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.util.UiUtil;

/**
 * Created by HuongTrung on 22/02/17.
 */

public class SearchResultAdapter extends AdapterWithItemClick<OnClickViewHolder> {
    public static final int HEADER = 0;
    public static final int NEWS = 1;
    public static final int ADVANTAGES = 2;
    public static final int MEMBER = 3;

    private List<SearchResultBean> searchResultBeanList;

    public SearchResultAdapter(List<SearchResultBean> searchResultBeanList) {
        this.searchResultBeanList = searchResultBeanList;
    }

    @Override
    public OnClickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER:
                return new StickHeaderSearchViewHolder(UiUtil.inflate(parent, StickHeaderSearchViewHolder.LAYOUT_ID, false));
            case NEWS:
                return new SearchListViewHolder(UiUtil.inflate(parent, SearchListViewHolder.LAYOUT_ID, false));
            case ADVANTAGES:
                return new SearchListViewHolder(UiUtil.inflate(parent, SearchListViewHolder.LAYOUT_ID, false));
            case MEMBER:
                return new SearchListViewHolder(UiUtil.inflate(parent, SearchListViewHolder.LAYOUT_ID, false));
            default:
                return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        SearchResultBean searchResultBean = searchResultBeanList.get(position);
        if (searchResultBean.headerTitle != null) {
            return HEADER;
        } else if (searchResultBean.advantagesBean != null) {
            return ADVANTAGES;
        } else if (searchResultBean.memberBean != null) {
            return MEMBER;
        } else if (searchResultBean.newBean != null) {
            return NEWS;
        } else
            return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(OnClickViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        SearchResultBean searchResultBean = searchResultBeanList.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case HEADER:
                ((StickHeaderSearchViewHolder) holder).bind(searchResultBean.headerTitle);
                break;
            case ADVANTAGES:
                ((SearchListViewHolder) holder).bind(searchResultBean.advantagesBean);
                break;
            case NEWS:
                ((SearchListViewHolder) holder).bind(searchResultBean.newBean);
                break;
            case MEMBER:
                ((SearchListViewHolder) holder).bind(searchResultBean.memberBean);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return searchResultBeanList.size();
    }
}
