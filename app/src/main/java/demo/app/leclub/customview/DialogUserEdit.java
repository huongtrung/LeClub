package demo.app.leclub.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.app.leclub.R;
import demo.app.leclub.bean.UserProfileItemBean;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 23/02/17.
 */

public class DialogUserEdit extends Dialog {
    @Nullable
    @BindView(R.id.title_label)
    TextView TitleLabel;
    @Nullable
    @BindView(R.id.edit_value)
    EditText etEditValue;

    int position;

    UserProfileItemBean userProfileItemBean;
    InputStringListener inputStringListener;

    public void setInputStringListener(InputStringListener inputStringListener) {
        this.inputStringListener = inputStringListener;
    }

    public DialogUserEdit(Context context, UserProfileItemBean userProfileItemBean, int position) {
        super(context);
        this.userProfileItemBean = userProfileItemBean;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialogView();
        ButterKnife.bind(this);
    }

    private void initDialogView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog_user_edit);
        final Window window = getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(0));
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
        StringUtil.displayText(userProfileItemBean.label, TitleLabel);
        StringUtil.displayText(userProfileItemBean.value, etEditValue);
    }

    @OnClick(R.id.dialog_user_profile_edit_cancel)
    void cancelDialog() {
        dismiss();
    }

    @OnClick(R.id.dialog_user_profile_edit_ok)
    void okDialog() {
        dismiss();
        if (inputStringListener != null) {
            inputStringListener.inputString(position, etEditValue.getText().toString());
        }
    }

    public interface InputStringListener {
        void inputString(int position, String value);
    }
}
