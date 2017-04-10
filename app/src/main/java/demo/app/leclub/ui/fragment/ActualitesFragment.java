package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.api.request.NewListRequest;
import demo.app.leclub.api.response.NewListResponse;
import demo.app.leclub.bean.NewBean;
import demo.app.leclub.ui.adapter.NewListAdapter;
import vn.app.base.adapter.DividerItemDecoration;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.FragmentUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActualitesFragment extends HeaderFragment {

    private NewListAdapter newListAdapter;
    private List<NewBean> newBeanList;

    @BindView(R.id.rc_actualites)
    RecyclerView rcNewList;

    public static ActualitesFragment newInstance() {
        ActualitesFragment fragment = new ActualitesFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_actualites;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rcNewList.setLayoutManager(new LinearLayoutManager(getContext()));
        rcNewList.addItemDecoration(new DividerItemDecoration(getContext(), null));
    }

    @Override
    protected void initData() {
        if (newBeanList == null)
            getNewsData();
        else
            handleNewsData(newBeanList);
    }

    @Override
    protected boolean isStartWithLoading() {
        return newBeanList == null;
    }

    private void getNewsData() {
        NewListRequest newListRequest = new NewListRequest();
        newListRequest.setRequestCallBack(new ApiObjectCallBack<NewListResponse>() {
            @Override
            public void onSuccess(NewListResponse data) {
                initialResponseHandled();
                handleNewsData(data.news);
            }
            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        newListRequest.execute();
    }

    private void handleNewsData(List<NewBean> inNewBeanList) {
        newBeanList = inNewBeanList;
        newListAdapter = new NewListAdapter(newBeanList);
        rcNewList.setAdapter(newListAdapter);
        newListAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                gotoNewsDetail(newBeanList.get(position));
            }
        });
    }

    private void gotoNewsDetail(NewBean newBean) {
        FragmentUtil.pushFragment(getActivity(), ActualitesDetailFragment.newInstance(newBean), null);
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.title_screen_Actualites);
    }
}
