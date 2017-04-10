package demo.app.leclub.ui.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.api.request.AdvantagesListRequest;
import demo.app.leclub.api.response.AdvantagesListResponse;
import demo.app.leclub.bean.AdvantagesBean;
import demo.app.leclub.callback.OpenWebPage;
import demo.app.leclub.ui.adapter.AdvantagesListAdapter;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.FragmentUtil;
import vn.app.base.util.IntentUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvantagesFragment extends HeaderFragment {
    private List<AdvantagesBean> advantagesBean;
    AdvantagesListAdapter advantagesListAdapter;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rc_list_advantages)
    RecyclerView rcAdvantages;

    public static AdvantagesFragment newInstance() {
        AdvantagesFragment fragment = new AdvantagesFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_avantages;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rcAdvantages.setLayoutManager(new LinearLayoutManager(getContext()));
        showOrHideWhenScroll();
    }

    @Override
    protected void initData() {
        if (advantagesBean == null) {
            getAdvantagesData();
        } else {
            handleAdvantagesData(advantagesBean);
        }
    }

    @Override
    protected boolean isStartWithLoading() {
        return advantagesBean == null;
    }

    private void getAdvantagesData() {
        AdvantagesListRequest advantagesListRequest = new AdvantagesListRequest();
        advantagesListRequest.setRequestCallBack(new ApiObjectCallBack<AdvantagesListResponse>() {
            @Override
            public void onSuccess(AdvantagesListResponse data) {
                initialResponseHandled();
                handleAdvantagesData(data.advantages);
            }

            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        advantagesListRequest.execute();
    }

    private void handleAdvantagesData(final List<AdvantagesBean> advantagesBeanList) {
        this.advantagesBean = advantagesBeanList;
        advantagesListAdapter = new AdvantagesListAdapter(advantagesBeanList);
        rcAdvantages.setAdapter(advantagesListAdapter);
        advantagesListAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                gotoDetailScreen(advantagesBean.get(position));
            }
        });
        advantagesListAdapter.setOpenWebPage(new OpenWebPage() {
            @Override
            public void openWeb(String url) {
                IntentUtil.openWebPage(getActivity(), url);
            }
        });
    }

    private void gotoDetailScreen(AdvantagesBean advantagesBean) {
        FragmentUtil.pushFragment(getActivity(), AdvantagesDetailFragment.newInstance(advantagesBean), null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showOrHideWhenScroll() {
        rcAdvantages.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    fab.hide();
                else
                    fab.show();
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.title_screen_advantages);
    }
}
