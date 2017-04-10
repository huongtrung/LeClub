package demo.app.leclub.customview;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import demo.app.leclub.R;
import vn.app.base.imageloader.ImageLoader;

/**
 * Created by HuongTrung on 24/02/17.
 */

public class DialogImageViewer extends Dialog {
    @Nullable
    @BindView(R.id.iv_image_viewer)
    ImageView ivImageViewer;

    String url;

    public DialogImageViewer(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialogView();
        ButterKnife.bind(this);
    }

    private void initDialogView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_image_viewer);
        final Window window = getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
        ImageLoader.loadImage(getContext(), R.drawable.loading_list_image_220, url, ivImageViewer);
    }
    @Optional
    @OnClick(R.id.iv_icon_close)
    void closeImage() {
        dismiss();
    }
}
