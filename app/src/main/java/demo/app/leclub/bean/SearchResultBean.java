package demo.app.leclub.bean;

import android.support.v4.app.FragmentActivity;

import demo.app.leclub.ui.fragment.ActualitesDetailFragment;
import demo.app.leclub.ui.fragment.AdvantagesDetailFragment;
import demo.app.leclub.ui.fragment.MemberDetailFragment;
import vn.app.base.util.FragmentUtil;

/**
 * Created by HuongTrung on 22/02/17.
 */

public class SearchResultBean {
    public String headerTitle;
    public NewBean newBean;
    public MemberBean memberBean;
    public AdvantagesBean advantagesBean;

    public SearchResultBean(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public SearchResultBean(NewBean newBean) {
        this.newBean = newBean;
    }

    public SearchResultBean(MemberBean memberBean) {
        this.memberBean = memberBean;
    }

    public SearchResultBean(AdvantagesBean advantagesBean) {
        this.advantagesBean = advantagesBean;
    }

    public void goToDetail(FragmentActivity activity) {
        if (newBean != null) {
            goToActualitesDetail(activity, newBean);
        } else if (memberBean != null) {
            goToMemberDetail(activity, memberBean);
        } else if (advantagesBean != null) {
            goToAdvantagesDetail(activity, advantagesBean);
        }
    }

    private void goToMemberDetail(FragmentActivity activity, MemberBean memberBean) {
        FragmentUtil.pushFragment(activity, MemberDetailFragment.newInstance(memberBean), null);
    }

    private void goToActualitesDetail(FragmentActivity activity, NewBean newsBean) {
        FragmentUtil.pushFragment(activity, ActualitesDetailFragment.newInstance(newsBean), null);
    }

    private void goToAdvantagesDetail(FragmentActivity activity, AdvantagesBean avantageBean) {
        FragmentUtil.pushFragment(activity, AdvantagesDetailFragment.newInstance(avantageBean), null);
    }
}
