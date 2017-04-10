package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.api.request.MemberListRequest;
import demo.app.leclub.api.response.MemberListResponse;
import demo.app.leclub.bean.MemberBean;
import demo.app.leclub.callback.OnMemberInteract;
import demo.app.leclub.customview.DialogInteract;
import demo.app.leclub.ui.adapter.MemberListAdapter;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.DialogUtil;
import vn.app.base.util.FragmentUtil;
import vn.app.base.util.IntentUtil;
import vn.app.base.util.StringUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberFragment extends HeaderFragment {

    @BindView(R.id.rc_member)
    RecyclerView rcMember;
    private List<MemberBean> memberBean;
    private MemberListAdapter memberListAdapter;

    public static MemberFragment newInstance() {
        MemberFragment fragment = new MemberFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rcMember.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        if (memberBean == null)
            getMemberData();
        else
            handleMemberData(memberBean);
    }

    private void getMemberData() {
        MemberListRequest memberListRequest = new MemberListRequest();
        memberListRequest.setRequestCallBack(new ApiObjectCallBack<MemberListResponse>() {
            @Override
            public void onSuccess(MemberListResponse data) {
                initialResponseHandled();
                handleMemberData(data.members);
            }

            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        memberListRequest.execute();
    }

    private void handleMemberData(final List<MemberBean> inMemberList) {
        this.memberBean = inMemberList;
        memberListAdapter = new MemberListAdapter(inMemberList, new OnMemberInteract() {
            @Override
            public void onCallPhone(MemberBean memberBean) {
                if (StringUtil.checkStringValid(memberBean.cProTelephone)) {
                    DialogInteract interact = new DialogInteract(getContext(), getActivity(), memberBean.cProTelephone);
                    interact.show();
                } else {
                    showDialog(getString(R.string.no_phone_number));
                }
            }

            @Override
            public void onSendMail(MemberBean memberBean) {
                if (StringUtil.checkStringValid(memberBean.cProEmail)) {
                    IntentUtil.openSendMail(getActivity(),memberBean.cProEmail);
                } else {
                    showDialog(getString(R.string.no_email_address));
                }
            }
        });
        rcMember.setAdapter(memberListAdapter);
        memberListAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                gotoMemberDetailScreen(memberBean.get(position));
            }
        });
    }

    private void gotoMemberDetailScreen(MemberBean memberBean) {
        FragmentUtil.pushFragment(getActivity(), MemberDetailFragment.newInstance(memberBean), null);
    }

    private void showDialog(String message) {
        DialogUtil.showOkBtnDialog(getContext(), getString(R.string.app_name), message);
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.title_screen_member);
    }

    @Override
    protected boolean isStartWithLoading() {
        return memberBean == null;
    }
}
