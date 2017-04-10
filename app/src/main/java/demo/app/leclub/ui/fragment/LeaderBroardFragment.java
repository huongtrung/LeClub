package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.api.request.LeaderBoardRequest;
import demo.app.leclub.api.response.LeaderBoadResponse;
import demo.app.leclub.bean.LeaderBoardBean;
import demo.app.leclub.bean.MemberBean;
import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.constants.LeaderBoardType;
import demo.app.leclub.manager.UserManager;
import demo.app.leclub.ui.adapter.LeaderBoardAdapter;
import vn.app.base.adapter.DividerItemDecoration;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.FragmentUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBroardFragment extends HeaderFragment {
    @BindView(R.id.rc_leader_broard)
    RecyclerView rvLeaderBoard;

    LeaderBoardAdapter leaderBoardAdapter;

    List<LeaderBoardBean> leaderboardBeanList;

    UserProfileBean userProfileBean;

    public static LeaderBroardFragment newInstance() {
        LeaderBroardFragment fragment = new LeaderBroardFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_leader_broard;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rvLeaderBoard.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLeaderBoard.addItemDecoration(new DividerItemDecoration(getContext(), null));
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {
        if (leaderboardBeanList == null) {
            getLeaderBoard();
            userProfileBean = UserManager.getCurrentUser();
        } else {
            setLeaderBoardList(leaderboardBeanList, leaderBoardAdapter.leaderBoardType);
        }
    }

    private void getLeaderBoard() {
        LeaderBoardRequest request = new LeaderBoardRequest();
        request.setRequestCallBack(new ApiObjectCallBack<LeaderBoadResponse>() {
            @Override
            public void onSuccess(LeaderBoadResponse data) {
                initialResponseHandled();
                setLeaderBoardList(data.leaderBoardBean, LeaderBoardType.Brut);
            }

            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        request.execute();
    }

    private void setLeaderBoardList(List<LeaderBoardBean> leaderboardData, LeaderBoardType leaderboardType) {
        if (leaderboardData != null && !leaderboardData.isEmpty()) {
            leaderboardBeanList = leaderboardData;
            leaderBoardAdapter = new LeaderBoardAdapter(leaderboardType);
            leaderBoardAdapter.setHeader(userProfileBean);
            leaderBoardAdapter.setItems(leaderboardBeanList);
            rvLeaderBoard.setAdapter(leaderBoardAdapter);
            leaderBoardAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
                @Override
                public void onItemClick(View view, int position) {
                    LeaderBoardBean selectItem = leaderboardBeanList.get(position - 1);
                    FragmentUtil.pushFragment(getActivity(), MemberDetailFragment.newInstance(new MemberBean(selectItem)), null);
                }
            });
        }
    }

    @Override
    protected boolean isStartWithLoading() {
        return leaderboardBeanList == null;
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.title_screen_leader_broard);
    }
}
