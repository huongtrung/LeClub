package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.api.request.SearchListRequest;
import demo.app.leclub.api.response.SearchListResponse;
import demo.app.leclub.bean.SearchBean;
import demo.app.leclub.bean.SearchResultBean;
import demo.app.leclub.constants.HeaderOption;
import demo.app.leclub.ui.adapter.SearchResultAdapter;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.KeyboardUtil;
import vn.app.base.util.StringUtil;

public class SearchFragment extends HeaderFragment implements OnRecyclerViewItemClick {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rc_search)
    RecyclerView rcSearch;
    SearchResultAdapter searchResultAdapter;
    List<SearchResultBean> searchResultBeanList;
    SearchBean searchBean;

    public static final String SEARCH_TEXT = "SEARCH_TEXT";
    private String currentKeyword;

    public static SearchFragment newInstance(String txtSearch) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_TEXT, txtSearch);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchData();
                    return true;
                }
                return false;
            }
        });
        rcSearch.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        currentKeyword = bundle.getString(SEARCH_TEXT, null);
    }

    @Override
    protected void initData() {
        if (StringUtil.checkStringValid(currentKeyword))
            etSearch.setText(currentKeyword);
        else
            etSearch.setHint(getString(R.string.title_screen_search));
        if (searchBean != null) {
            handleDataSearchResult(searchBean);
        } else {
            searchData();
        }
    }

    private void searchData() {
        showCoverNetworkLoading();
        currentKeyword = etSearch.getText().toString().trim();
        SearchListRequest searchListRequest = new SearchListRequest(currentKeyword);
        searchListRequest.setRequestCallBack(new ApiObjectCallBack<SearchListResponse>() {
            @Override
            public void onSuccess(SearchListResponse data) {
                hideCoverNetworkLoading();
                handleDataSearchResult(data.search);
            }

            @Override
            public void onFail(int failCode, String message) {
                hideCoverNetworkLoading();
                initialNetworkError();
            }
        });
        searchListRequest.execute();
        KeyboardUtil.hideKeyboard(getActivity());
    }

    private void handleDataSearchResult(SearchBean searchBean) {
        if (searchBean != null) {
            this.searchBean = searchBean;
            searchResultBeanList = new ArrayList<>();
            if (searchBean != null && !searchBean.news.isEmpty()) {
                searchResultBeanList.add(new SearchResultBean("Actualités"));
                searchResultBeanList.addAll(searchBean.getNewList());
            }
            if (searchBean != null && !searchBean.members.isEmpty()) {
                searchResultBeanList.add(new SearchResultBean("Membres"));
                searchResultBeanList.addAll(searchBean.getMemberList());
            }
            if (searchBean != null && !searchBean.news.isEmpty()) {
                searchResultBeanList.add(new SearchResultBean("Avantages"));
                searchResultBeanList.addAll(searchBean.getAdvantagesList());
            }
            searchResultAdapter = new SearchResultAdapter(searchResultBeanList);
            rcSearch.setAdapter(searchResultAdapter);
            searchResultAdapter.setOnRecyclerViewItemClick(this);
        }
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.title_screen_search);
    }

    @Override
    protected int getLeftIcon() {
        return HeaderOption.LEFT_BACK;
    }

    @Override
    protected int getRightIcon() {
        return HeaderOption.RIGHT_NO_OPTION;
    }

    @Override
    public void onItemClick(View view, int position) {
        SearchResultBean itemBean = searchResultBeanList.get(position);
        itemBean.goToDetail(getActivity());
    }
}