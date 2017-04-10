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
 * Created by HuongTrung on 21/02/17.
 */

public class MemberDetailHeaderViewHolder extends OnClickViewHolder {

    public static final int LAYOUT_ID = R.layout.item_member_detail_header;

    @BindView(R.id.tv_member_name)
    TextView tvMemberName;
    @BindView(R.id.tv_member_status)
    TextView tvMemberStatus;
    @BindView(R.id.civ_member_detail_avatar)
    CircleImageView civMemberAvatar;

    MemberBean memberBean;
    OnMemberInteract interact;

    public MemberDetailHeaderViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(MemberBean memberBean, OnMemberInteract interact) {
        this.memberBean = memberBean;
        this.interact = interact;
        ImageLoader.loadImage(itemView.getContext(), memberBean.photo, civMemberAvatar);
        StringUtil.displayText(memberBean.getFullName(), tvMemberName);
        StringUtil.displayText(memberBean.statut, tvMemberStatus);
    }

    @OnClick(R.id.ll_icon_phone)
    void callPhone() {
        if (interact != null) {
            interact.onCallPhone(memberBean);
        }
    }

    @OnClick(R.id.ll_icon_mail)
    void sendMail() {
        if (interact != null) {
            interact.onSendMail(memberBean);
        }
    }
}
