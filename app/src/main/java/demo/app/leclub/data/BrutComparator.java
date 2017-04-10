package demo.app.leclub.data;

import java.util.Comparator;

import demo.app.leclub.bean.LeaderBoardBean;

/**
 * Created on 7/24/2016.
 */
public class BrutComparator implements Comparator<LeaderBoardBean> {

    @Override
    public int compare(LeaderBoardBean leaderboardBean, LeaderBoardBean t1) {
        int currentBrut = Integer.parseInt(leaderboardBean.brut);
        int t1Brut = Integer.parseInt(t1.brut);
        String currentPreName = leaderboardBean.prenom;
        String t1PreName = t1.prenom;
        if (currentBrut < t1Brut) {
            return 1;
        } else if (currentBrut > t1Brut) {
            return -1;
        } else {
            int compareData = currentPreName.compareTo(t1PreName);
            if (compareData == 0) {
                String currentName = leaderboardBean.nom;
                String t1Name = t1.nom;
                return currentName.compareTo(t1Name) * -1;
            } else {
                return compareData * -1;
            }
        }
    }
}
