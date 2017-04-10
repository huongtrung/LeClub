package demo.app.leclub.customview;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.app.leclub.R;
import vn.app.base.util.IntentUtil;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 22/02/17.
 */

public class DialogUserInfo extends Dialog {
    @BindView(R.id.tv_dialog_member_label)
    TextView tvDialogMemberLabel;
    @BindView(R.id.tv_dialog_member_value)
    TextView tvDialogMemberValue;

    private Activity activity;
    private String label;
    private String value;
    boolean clickable;

    public DialogUserInfo(Context context, String label, String value,boolean clickable, Activity activity) {
        super(context);
        this.label = label;
        this.value = value;
        this.clickable = clickable;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        innitDialogView();
        ButterKnife.bind(this);
    }

    private void innitDialogView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_user_info);
        final Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void show() {
        super.show();
        StringUtil.displayText(label, tvDialogMemberLabel);
        StringUtil.displayText(value, tvDialogMemberValue);
    }

    @OnClick(R.id.tv_dialog_member_value)
    void openWeb() {
        if (clickable && StringUtil.checkStringValid(value)) {
            IntentUtil.openWebPage(activity, value);
        }
    }

    @OnClick(R.id.bt_ok)
    void dismissDialog() {
        dismiss();
    }
}
