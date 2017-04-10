package demo.app.leclub.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import demo.app.leclub.R;
import demo.app.leclub.bean.MemberBean;
import demo.app.leclub.bean.MemberDetailItemBean;
import demo.app.leclub.callback.OnMemberInteract;
import demo.app.leclub.constants.HeaderOption;
import demo.app.leclub.customview.DialogInteract;
import demo.app.leclub.customview.DialogUserInfo;
import demo.app.leclub.ui.adapter.MemberDetailAdapter;
import vn.app.base.adapter.DividerItemDecoration;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.DialogUtil;
import vn.app.base.util.IntentUtil;
import vn.app.base.util.StringUtil;
import vn.app.base.util.UiUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MemberDetailFragment extends HeaderFragment implements OnMemberInteract, OnRecyclerViewItemClick {
    MemberBean memberBean;
    List<MemberDetailItemBean> itemBeanList;
    MemberDetailAdapter memberDetailAdapter;
    private static final String KEY_MEMBER_DETAIL = "KEY_MEMBER_DETAIL";
    @BindView(R.id.rc_member_detail)
    RecyclerView rcMemberDetail;

    public static MemberDetailFragment newInstance(MemberBean memberBean) {
        MemberDetailFragment fragment = new MemberDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_MEMBER_DETAIL, memberBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rcMemberDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        rcMemberDetail.addItemDecoration(new DividerItemDecoration(null));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_member_detail;
    }

    @Override
    protected void getArgument(Bundle bundle) {
        memberBean = bundle.getParcelable(KEY_MEMBER_DETAIL);
    }

    @Override
    protected void initData() {
        if (itemBeanList == null) {
            setUpData();
        } else {
            setUserData();
        }
    }

    private void setUserData() {
        memberDetailAdapter = new MemberDetailAdapter();
        memberDetailAdapter.setHeader(memberBean);
        memberDetailAdapter.setItems(itemBeanList);
        rcMemberDetail.setAdapter(memberDetailAdapter);
        memberDetailAdapter.setOnMemberInteract(this);
        memberDetailAdapter.setOnRecyclerViewItemClick(this);
    }

    private void setUpData() {
        if (itemBeanList != null) {
            itemBeanList.clear();
        } else {
            itemBeanList = new ArrayList<>();
        }
        itemBeanList = new ArrayList<>();
        if (StringUtil.checkStringValid(memberBean.titre)) {
            itemBeanList.add(new MemberDetailItemBean("TITRE", memberBean.titre));
        }
        if (StringUtil.checkStringValid(memberBean.centreInterets)) {
            itemBeanList.add(new MemberDetailItemBean("CENTRES D’INTÉRÊT", memberBean.centreInterets));
        }
        if (StringUtil.checkStringValid(memberBean.linkedin)) {
            itemBeanList.add(new MemberDetailItemBean("LINKEDIN", memberBean.linkedin, true));
        }
        if (StringUtil.checkStringValid(memberBean.societe)) {
            itemBeanList.add(new MemberDetailItemBean("SOCIÉTÉ", memberBean.societe));
        }
        if (StringUtil.checkStringValid(memberBean.siteWeb)) {
            itemBeanList.add(new MemberDetailItemBean("SITE WEB", memberBean.siteWeb, true));
        }
        if (StringUtil.checkStringValid(memberBean.secteurActivite)) {
            itemBeanList.add(new MemberDetailItemBean("ACTIVITÉ", memberBean.secteurActivite));
        }
        if (StringUtil.checkStringValid(memberBean.description)) {
            itemBeanList.add(new MemberDetailItemBean("DESCRIPTION", memberBean.description));
        }
        if (StringUtil.checkStringValid(memberBean.numLicence)) {
            itemBeanList.add(new MemberDetailItemBean("NUMÉRO DE LICENCE", memberBean.numLicence));
        }
        if (StringUtil.checkStringValid(memberBean.index)) {
            itemBeanList.add(new MemberDetailItemBean("INDEX", memberBean.index));
        }
        if (StringUtil.checkStringValid(memberBean.membreDe)) {
            itemBeanList.add(new MemberDetailItemBean("JOUEUR AU GOLF DE", memberBean.membreDe));
        }
        if (memberDetailAdapter != null) {
            memberDetailAdapter.setHeader(memberBean);
            memberDetailAdapter.notifyDataSetChanged();
        } else {
            setUserData();
        }
    }


    @Override
    public String getScreenTitle() {
        return getString(R.string.title_screen_profile);
    }

    @Override
    public void onCallPhone(MemberBean memberBean) {
        if (StringUtil.checkStringValid(memberBean.cProTelephone)) {
            DialogInteract interact = new DialogInteract(getContext(), getActivity(), memberBean.cProTelephone);
            interact.show();
        } else {
            showDialog(getString(R.string.no_phone_number));
        }
    }

    private void showDialog(String message) {
        DialogUtil.showOkBtnDialog(getContext(), getString(R.string.app_name), message);
    }

    @Override
    public void onSendMail(MemberBean memberBean) {
        if (StringUtil.checkStringValid(memberBean.cProEmail)) {
            IntentUtil.openSendMail(getActivity(), memberBean.cProEmail);
        } else {
            showDialog(getString(R.string.no_email_address));
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        MemberDetailItemBean clickItem = itemBeanList.get(UiUtil.isTablet(getResources()) ? position : position - 1);
        DialogUserInfo dialogUserInfo = new DialogUserInfo(getContext(), clickItem.label, clickItem.value, clickItem.isClickable, getActivity());
        dialogUserInfo.show();
    }

    @Override
    protected int getLeftIcon() {
        return HeaderOption.LEFT_BACK;
    }
}
