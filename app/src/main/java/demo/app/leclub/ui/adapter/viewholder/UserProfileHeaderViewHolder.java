package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import demo.app.leclub.R;
import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.callback.OnUserEdit;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 23/02/17.
 */

public class UserProfileHeaderViewHolder extends OnClickViewHolder {
    public static final int LAYOUT_ID = R.layout.item_user_profile_header;
    @BindView(R.id.civ_user_avatar)
    CircleImageView civUserAvatar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_title)
    TextView tvUserTitle;

    OnUserEdit onUserEdit;

    public void setOnUserEdit(OnUserEdit onUserEdit) {
        this.onUserEdit = onUserEdit;
    }

    UserProfileBean userProfileBean;

    public UserProfileHeaderViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(UserProfileBean userProfileBean) {
        this.userProfileBean = userProfileBean;
        ImageLoader.loadImage(itemView.getContext(), R.drawable.loading_list_image_113, userProfileBean.photo, civUserAvatar);
        StringUtil.displayText(userProfileBean.getFullName(), tvUserName);
        StringUtil.displayText(userProfileBean.titre, tvUserTitle);
    }

    @OnClick(R.id.iv_icon_camera)
    void pickPhoto() {
        if (onUserEdit != null) {
            onUserEdit.onChangePhoto(getAdapterPosition());
        }
    }
    @OnClick(R.id.civ_user_avatar)
    void clickPhoto(){
        if (onUserEdit !=null){
            onUserEdit.onClickPhoto(userProfileBean.photo);
        }
    }
}
