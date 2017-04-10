package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import demo.app.leclub.R;
import demo.app.leclub.bean.LeaderBoardBean;
import demo.app.leclub.constants.LeaderBoardType;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 28/02/17.
 */

public class LeaderBoadListViewHolder extends OnClickViewHolder {
    public static final int LAYOUT_ID = R.layout.item_leader_board;
    @BindView(R.id.leaderboard_rank)
    TextView tvRank;
    @BindView(R.id.leaderboard_avatar)
    CircleImageView civAvatar;
    @BindView(R.id.leaderboard_name)
    TextView tvName;
    @BindView(R.id.leaderboard_score)
    TextView tvScore;

    public LeaderBoadListViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(LeaderBoardBean leaderBoardBean, LeaderBoardType leaderBoardType) {
        ImageLoader.loadImage(itemView.getContext(), leaderBoardBean.photo, civAvatar);
        StringUtil.displayText(getAdapterPosition() + "", tvRank);
        StringUtil.displayText(leaderBoardBean.getFullName(), tvName);
        String value = "";
        switch (leaderBoardType) {
            case Brut:
                value = leaderBoardBean.brut;
                break;
            case Net:
                value = leaderBoardBean.net;
                break;
        }
        StringUtil.displayText(value + "pts", tvScore);
    }
}
