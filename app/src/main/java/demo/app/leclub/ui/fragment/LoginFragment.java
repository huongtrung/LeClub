package demo.app.leclub.ui.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.api.request.LoginRequest;
import demo.app.leclub.api.response.LoginResponse;
import demo.app.leclub.manager.UserManager;
import vn.app.base.api.volley.callback.ApiObjectCallBack;
import vn.app.base.util.DialogUtil;
import vn.app.base.util.FragmentUtil;
import vn.app.base.util.KeyboardUtil;
import vn.app.base.util.SharedPrefUtils;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 08/02/17.
 */

public class LoginFragment extends NoHeaderFragment {
    @BindView(R.id.et_username_login)
    EditText etUserName;
    @BindView(R.id.et_pass_login)
    EditText etPassLogin;
    @BindView(R.id.bt_login)
    Button btLogin;

    private String userName;
    private String password;
    private LoginResponse loginResponse;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

//    @OnTextChanged(R.id.et_username_login)
//    void textChangeName(CharSequence text){
//        userName = text.toString().trim();
//        toggleLogin();
//    }
//    @OnTextChanged(R.id.et_pass_login)
//    void textChangePass(CharSequence text){
//        password = text.toString().trim();
//        toggleLogin();
//    }

    private void toggleLogin(){
        if (StringUtil.checkStringValid(userName) && StringUtil.checkStringValid(password)){
            btLogin.setEnabled(true);
        }
        else {
            btLogin.setEnabled(false);
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(View root) {
        super.initView(root);
        etPassLogin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    loginApp();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.bt_login)
    public void login() {
        userName = etUserName.getText().toString().trim();
        password = etPassLogin.getText().toString().trim();
        if (!StringUtil.checkStringValid(userName) || !StringUtil.checkStringValid(password)) {
            DialogUtil.showOkBtnDialog(getContext(), getString(R.string.dialog_input_error), getString(R.string.dialog_input_error_content));
            return;
        }
        loginApp();
    }

    private void loginApp() {
        showCoverNetworkLoading();
        KeyboardUtil.hideKeyboard(getActivity());
        LoginRequest loginRequest = new LoginRequest(userName, password);
        loginRequest.setRequestCallBack(new ApiObjectCallBack<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse data) {
                hideCoverNetworkLoading();
                loginResponse = data;
                handleLoginSuccess();
            }

            @Override
            public void onFail(int failCode, String message) {
                hideCoverNetworkLoading();
            }
        });
        loginRequest.execute();

    }

    private void handleLoginSuccess() {
        if (loginResponse.userProfile != null) {
            SharedPrefUtils.saveAccessToken(loginResponse.token);
            UserManager.saveCurrentUser(loginResponse.userProfile);
            FragmentUtil.pushFragment(getActivity(), AdFragment.newInstance(false), null);
        }
    }
}
