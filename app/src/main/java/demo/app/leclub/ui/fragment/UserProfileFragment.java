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
import demo.app.leclub.api.request.UpdateUserProfileRequest;
import demo.app.leclub.api.request.UserProfileRequest;
import demo.app.leclub.api.response.LoginResponse;
import demo.app.leclub.api.response.UpdateUserProfileResponse;
import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.bean.UserProfileItemBean;
import demo.app.leclub.callback.OnUserEdit;
import demo.app.leclub.constants.ProfileConstant;
import demo.app.leclub.customview.DialogImageViewer;
import demo.app.leclub.customview.DialogUserEdit;
import demo.app.leclub.customview.DialogUserInfo;
import demo.app.leclub.manager.UserManager;
import demo.app.leclub.ui.adapter.UserProfileAdapter;
import vn.app.base.adapter.DividerItemDecoration;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.callback.OnRecyclerViewItemClick;
import vn.app.base.util.FragmentUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends HeaderFragment implements OnUserEdit, OnRecyclerViewItemClick {

    public static final String USER_PHOTO = "USER_PHOTO";
    List<UserProfileItemBean> userProfileItemBeanList;
    UserProfileAdapter userProfileAdapter;
    UserProfileBean userProfileBean;
    @BindView(R.id.rc_user_profile)
    RecyclerView rcUserProfile;

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        rcUserProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        rcUserProfile.addItemDecoration(new DividerItemDecoration(null));
    }

    @Override
    protected void initData() {
        if (userProfileItemBeanList == null) {
            getProfileData();
        } else {
            handleProfileData();
        }
    }

    public void getProfileData() {
        UserProfileRequest userProfileRequest = new UserProfileRequest();
        userProfileRequest.setRequestCallBack(new ApiObjectCallBack<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse data) {
                initialResponseHandled();
                if (data != null && data.userProfile != null) {
                    UserManager.saveCurrentUser(data.userProfile);
                    userProfileBean = data.userProfile;
                } else {
                    userProfileBean = UserManager.getCurrentUser();
                }
                handleProfileData();
            }

            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        userProfileRequest.execute();
    }

    private void handleProfileData() {
        if (userProfileBean == null) {
            FragmentUtil.popBackStack(getActivity());
            return;
        }
        if (userProfileItemBeanList != null) {
            userProfileItemBeanList.clear();
        } else {
            userProfileItemBeanList = new ArrayList<>();
        }
        userProfileItemBeanList.add(new UserProfileItemBean("Général"));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "STATUT", userProfileBean.statut, ProfileConstant.STATUT));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "SOCIÉTÉ", userProfileBean.societe, ProfileConstant.SOCIETE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "SECTEUR D’ACTIVITÉ", userProfileBean.secteurActivite, ProfileConstant.SECTEURACTIVITE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "DESCRIPTION", userProfileBean.description, ProfileConstant.DESCRIPTION));
        userProfileItemBeanList.add(new UserProfileItemBean(true, true, "SITE WEB", userProfileBean.siteWeb, ProfileConstant.SITEWEB));
        userProfileItemBeanList.add(new UserProfileItemBean(false, "DATE DE NAISSANCE", userProfileBean.dateNaissance, ProfileConstant.DATENAISSANCE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "CENTRES D’INTÉRÊT", userProfileBean.centreInterets, ProfileConstant.CENTREINTERETS));
        userProfileItemBeanList.add(new UserProfileItemBean("Coordonnées professionnelles"));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "RUE", userProfileBean.cProRue, ProfileConstant.CPRORUE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "COMPLÉMENT", userProfileBean.cProComplement, ProfileConstant.CPROCOMPLEMENT));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "CODE POSTAL", userProfileBean.cProCodePostal, ProfileConstant.CPROCODEPOSTAL));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "TÉLÉPHONE", userProfileBean.cProTelephone, ProfileConstant.CPROTELEPHONE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "PORTABLE", userProfileBean.cProPortable, ProfileConstant.CPROPORTABLE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "EMAIL", userProfileBean.cProEmail, ProfileConstant.CPROEMAIL));
        userProfileItemBeanList.add(new UserProfileItemBean("Coordonnées personnelles"));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "PORTABLE", userProfileBean.cPersPortable, ProfileConstant.CPERSPORTABLE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "EMAIL", userProfileBean.cPersEmail, ProfileConstant.CPERSEMAIL));
        userProfileItemBeanList.add(new UserProfileItemBean("Réseaux sociaux"));
        userProfileItemBeanList.add(new UserProfileItemBean(true, true, "FACEBOOK", userProfileBean.facebook, ProfileConstant.FACEBOOK));
        userProfileItemBeanList.add(new UserProfileItemBean(true, true, "TWITTER", userProfileBean.twitter, ProfileConstant.TWITTER));
        userProfileItemBeanList.add(new UserProfileItemBean(true, true, "LINKEDIN", userProfileBean.linkedin, ProfileConstant.LINKEDIN));
        userProfileItemBeanList.add(new UserProfileItemBean(true, true, "INSTAGRAM", userProfileBean.instagram, ProfileConstant.INSTAGRAM));
        userProfileItemBeanList.add(new UserProfileItemBean(true, true, "SNAPCHAT", userProfileBean.snapchat, ProfileConstant.SNAPCHAT));
        userProfileItemBeanList.add(new UserProfileItemBean(true, true, "GOOGLE+", userProfileBean.googlePlus, ProfileConstant.GOOGLEPLUS));
        userProfileItemBeanList.add(new UserProfileItemBean("Golf"));
        userProfileItemBeanList.add(new UserProfileItemBean(false, "NUMÉRO DE LICENCE", userProfileBean.numLicence, ProfileConstant.NUMLICENCE));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "INDEX", userProfileBean.index, ProfileConstant.INDEX));
        userProfileItemBeanList.add(new UserProfileItemBean(true, "TAILLE DU POLO", userProfileBean.taillePolo, ProfileConstant.TAILLEPOLO));
        userProfileItemBeanList.add(new UserProfileItemBean(false, "MEMBRE DEPUIS", userProfileBean.dateMembre, ProfileConstant.DATEMEMBRE));
        userProfileItemBeanList.add(new UserProfileItemBean(false, "DATE DE RENOUVELLEMENT", userProfileBean.dateRenouvellement, ProfileConstant.DATERENOUVELLEMENT));
        userProfileItemBeanList.add(new UserProfileItemBean(false, "PARRAIN", userProfileBean.parrain, ProfileConstant.PARRAIN));
        userProfileItemBeanList.add(new UserProfileItemBean(false, "MEMBRE/JOUEUR AU GOLF DE", userProfileBean.membreDe, ProfileConstant.MEMBREDE));

        if (userProfileAdapter != null) {
            userProfileAdapter.setHeader(userProfileBean);
            userProfileAdapter.notifyDataSetChanged();
        } else {
            setUserData();
        }
    }

    private void setUserData() {
        userProfileAdapter = new UserProfileAdapter();
        userProfileAdapter.setHeader(userProfileBean);
        userProfileAdapter.setItems(userProfileItemBeanList);
        rcUserProfile.setAdapter(userProfileAdapter);
        userProfileAdapter.setOnUserEdit(this);
        userProfileAdapter.setOnRecyclerViewItemClick(this);
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.title_screen_mon_compte);
    }

    @Override
    public void onEdit(int position, final UserProfileItemBean userProfileItemBean) {
        DialogUserEdit dialogUserEdit = new DialogUserEdit(getContext(), userProfileItemBean, position - 1);
        dialogUserEdit.setInputStringListener(new DialogUserEdit.InputStringListener() {
            @Override
            public void inputString(int position, String value) {
                updateProfile(userProfileItemBeanList.get(position), value);
            }
        });
        dialogUserEdit.show();
    }

    private void updateProfile(UserProfileItemBean userProfileItemBean, String value) {
        showCoverNetworkLoading();
        UpdateUserProfileRequest request = new UpdateUserProfileRequest(userProfileItemBean.label, value);
        request.setRequestCallBack(new ApiObjectCallBack<UpdateUserProfileResponse>() {
            @Override
            public void onSuccess(UpdateUserProfileResponse data) {
                hideCoverNetworkLoading();
                if (data.updatedProfile != null) {
                    UserManager.saveCurrentUser(userProfileBean);
                    reInit(data.updatedProfile);
                }
            }

            @Override
            public void onFail(int failCode, String message) {
                initialNetworkError();
            }
        });
        request.execute();
    }

    private void reInit(UserProfileBean userProfileBean) {
        this.userProfileBean = userProfileBean;
        handleProfileData();
    }

    @Override
    public void onChangePhoto(int position) {

    }

    @Override
    public void onClickPhoto(String url) {
        DialogImageViewer imageViewer = new DialogImageViewer(getContext(), url);
        imageViewer.show();
    }

    @Override
    public void onItemClick(View view, int position) {
        UserProfileItemBean itemBean = userProfileItemBeanList.get(position - 1);
        DialogUserInfo dialogUserInfo = new DialogUserInfo(getContext(), itemBean.label, itemBean.value, itemBean.isClickable, getActivity());
        dialogUserInfo.show();
    }
}
