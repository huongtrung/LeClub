package demo.app.leclub.ui.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.manager.UserManager;
import vn.app.base.util.FragmentUtil;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 10/02/17.
 */

public class MenuFragment extends NoHeaderFragment {
    @BindView(R.id.et_menu_search)
    EditText etMenuSearch;
    @BindView(R.id.iv_menu_search)
    ImageView ivMenuSearch;

    private String txtSearchMenu;

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        etMenuSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    gotoSearchScreen(txtSearchMenu);
                    return true;
                }
                return false;
            }
        });
    }

    private void gotoSearchScreen(String txtSearch) {
        FragmentUtil.pushFragment(getActivity(), SearchFragment.newInstance(txtSearch), null);
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_menu_agenda, R.id.iv_menu_search, R.id.tv_menu_actualites,
            R.id.tv_menu_avantages, R.id.tv_menu_membres, R.id.tv_menu_moncompte,
            R.id.tv_menu_leaderbroard, R.id.tv_menu_deconnexion})
    void clickMenu(View v) {
        switch (v.getId()) {
            case R.id.iv_menu_search:
                txtSearchMenu = etMenuSearch.getText().toString().trim();
                if (StringUtil.checkStringValid(txtSearchMenu))
                    gotoSearchScreen(txtSearchMenu);
                else
                    etMenuSearch.setHint(getString(R.string.input_empty));
                break;
            case R.id.tv_menu_agenda:
                FragmentUtil.pushFragment(getActivity(), AgendaFragment.newInstance(), null);
                break;
            case R.id.tv_menu_actualites:
                FragmentUtil.pushFragment(getActivity(), ActualitesFragment.newInstance(), null);
                break;
            case R.id.tv_menu_avantages:
                FragmentUtil.pushFragment(getActivity(), AdvantagesFragment.newInstance(), null);
                break;
            case R.id.tv_menu_membres:
                FragmentUtil.pushFragment(getActivity(), MemberFragment.newInstance(), null);
                break;
            case R.id.tv_menu_moncompte:
                FragmentUtil.pushFragment(getActivity(), UserProfileFragment.newInstance(), null);
                break;
            case R.id.tv_menu_leaderbroard:
                FragmentUtil.pushFragment(getActivity(), LeaderBroardFragment.newInstance(), null);
                break;
            case R.id.tv_menu_deconnexion:
                UserManager.clearUserData();
                FragmentUtil.replaceFragment(getActivity(), LoginFragment.newInstance(), null);
                break;
        }
    }
}
