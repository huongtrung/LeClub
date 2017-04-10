package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import demo.app.leclub.R;
import demo.app.leclub.bean.MemberBean;
import demo.app.leclub.callback.OnMemberInteract;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 17/02/17.
 */

public class MemberListViewHolder extends OnClickViewHolder {

    public static final int LAYOUT_ID = R.layout.item_member_list;
    OnMemberInteract onMemberInteract;
    MemberBean memberBean;

    @BindView(R.id.civ_member_avatar)
    CircleImageView civMemberAvatar;
    @BindView(R.id.tv_fullname_member)
    TextView tvMemberName;
    @BindView(R.id.tv_member_description)
    TextView tvMemberDescription;

    public MemberListViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(MemberBean memberBean, OnMemberInteract onMemberInteract) {
        this.onMemberInteract = onMemberInteract;
        this.memberBean = memberBean;
        ImageLoader.loadImage(itemView.getContext(), R.drawable.loading_list_image_113, memberBean.photo, civMemberAvatar);
        StringUtil.displayText(memberBean.getFullName(), tvMemberName);
        StringUtil.displayText(memberBean.titre, tvMemberDescription);
    }

    @OnClick(R.id.iv_icon_phone)
    void callPhone() {
        if (onMemberInteract != null) {
            onMemberInteract.onCallPhone(memberBean);
        }
    }

    @OnClick(R.id.iv_icon_mail)
    void sendMail() {
        if (onMemberInteract != null) {
            onMemberInteract.onSendMail(memberBean);
        }
    }
}
