package cn.samir.online.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import cn.samir.online.R;
import cn.samir.online.http.ApiService;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.ui.fragment.RecyclerFragment;

/**
 * Created by xiaw on 2017/4/14 0014.
 */

public class HomeFollowFragment extends BaseFragment {

    public static final String TAG = HomeFollowFragment.class.getSimpleName();

    private RecyclerFragment recyclerFragment;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        if (recyclerFragment == null) {
            recyclerFragment = RecyclerFragment.newInstance(ApiService.followUrl);
        }

        FragmentTransaction t = getChildFragmentManager().beginTransaction();

        if (!recyclerFragment.isAdded()) {
            t.add(R.id.container, recyclerFragment, TAG);
        }

        t.show(recyclerFragment).commit();

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home_follow;
    }

    public static HomeFollowFragment newInstance() {
        return new HomeFollowFragment();
    }
}
