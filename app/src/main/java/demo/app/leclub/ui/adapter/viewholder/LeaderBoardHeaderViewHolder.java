package demo.app.leclub.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import demo.app.leclub.R;
import demo.app.leclub.bean.UserProfileBean;
import demo.app.leclub.constants.LeaderBoardType;
import demo.app.leclub.ui.adapter.LeaderBoardAdapter;
import vn.app.base.adapter.viewholder.OnClickViewHolder;
import vn.app.base.customview.SegmentedView;
import vn.app.base.imageloader.ImageLoader;
import vn.app.base.util.StringUtil;

/**
 * Created by HuongTrung on 28/02/17.
 */

public class LeaderBoardHeaderViewHolder extends OnClickViewHolder implements SegmentedView.SegmentedViewOnSelect {
    public static final int LAYOUT_ID = R.layout.item_leader_broad_header;
    private CircleImageView civLeaderBoard;
    private TextView tvUserBrut;
    private TextView tvUserBrutScore;
    private TextView tvUserNet;
    private TextView tvUserNetScore;
    private SegmentedView svScore;
    LeaderBoardAdapter leaderBoardAdapter;

    public LeaderBoardHeaderViewHolder(View itemView) {
        super(itemView);
        civLeaderBoard = (CircleImageView) itemView.findViewById(R.id.civ_leader_board);
        tvUserBrut = (TextView) itemView.findViewById(R.id.tv_user_brut);
        tvUserBrutScore = (TextView) itemView.findViewById(R.id.tv_user_brut_score);
        tvUserNet = (TextView) itemView.findViewById(R.id.tv_user_net);
        tvUserNetScore = (TextView) itemView.findViewById(R.id.tv_user_net_score);
        svScore = (SegmentedView) itemView.findViewById(R.id.leaderboard_tab);
    }

    public void bind(UserProfileBean userProfileBean, LeaderBoardAdapter leaderboardListAdapter) {
        this.leaderBoardAdapter = leaderboardListAdapter;
        ImageLoader.loadImage(itemView.getContext(), userProfileBean.photo, civLeaderBoard);
        StringUtil.displayTextHtml("8<sup><small>e</small></sup> Brut", tvUserBrut);
        StringUtil.displayTextHtml("5<sup><small>e</small></sup> Net", tvUserNet);
        StringUtil.displayText(userProfileBean.brut + "pts", tvUserBrutScore);
        StringUtil.displayText(userProfileBean.net + "pts", tvUserNetScore);

        if (leaderboardListAdapter.leaderBoardType == LeaderBoardType.Brut) {
            svScore.setSelectedTab(0);
        } else if (leaderboardListAdapter.leaderBoardType == LeaderBoardType.Net) {
            svScore.setSelectedTab(1);
        }
        svScore.setSegmentedViewOnSelect(this);
    }

    @Override
    public void onSegmentedViewSelect(int position) {
        if (leaderBoardAdapter == null) {
            return;
        }
        if (position == 0) {
            leaderBoardAdapter.changeSortType(LeaderBoardType.Brut);
        } else if (position == 1) {
            leaderBoardAdapter.changeSortType(LeaderBoardType.Net);
        }
    }
}
