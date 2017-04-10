package demo.app.leclub.customview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.app.leclub.R;
import vn.app.base.util.IntentUtil;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class DialogInteract extends Dialog {

    private Activity activity;
    private String phoneNumber;

    public DialogInteract(Context context, Activity activity, String phoneNumber) {
        super(context);
        this.activity = activity;
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialogView();
        ButterKnife.bind(this);
    }

    private void initDialogView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_member_interact);
        final Window window = getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(0));
        setCanceledOnTouchOutside(false);
    }
    @OnClick(R.id.tv_dialog_sms)
    void openSMS() {
        IntentUtil.openPhoneSms(activity, phoneNumber);
    }
    @OnClick(R.id.tv_dialog_call)
    void openCall() {
        IntentUtil.openPhoneCall(activity, phoneNumber);
    }
    @OnClick(R.id.tv_dismiss)
    void dismissDialog() {
        dismiss();
    }
}
