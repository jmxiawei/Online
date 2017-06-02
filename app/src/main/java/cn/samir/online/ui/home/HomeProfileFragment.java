package cn.samir.online.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.util.PhoneUtil;
import cn.samir.online.R;
import cn.samir.online.adapter.BaseRecyclerAdapter;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.adapter.holders.HomeProfileItemHolder;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.http.BaseHttpHandler;
import cn.samir.online.mvp.presenters.HomeProfilePresenter;
import cn.samir.online.ui.base.BaseFragment;
import cn.samir.online.views.CustomFontTextView;

/**
 * Created by xiaw on 2017/4/14 0014.
 */

public class HomeProfileFragment extends BaseFragment {


    public static final String TAG = HomeProfileFragment.class.getSimpleName();
    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.ll_reply)
    LinearLayout llReply;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    HomeProfilePresenter profilePresenter;
    ProfileAdapter profileAdapter;
    @BindView(R.id.tv_version)
    CustomFontTextView tvVersion;
    private ArrayList<ProfileItem> items = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        profileAdapter = new ProfileAdapter();
        recyclerView.setAdapter(profileAdapter);
        profileAdapter.setDataList(items);
        sdvHeader.setImageURI(Uri.parse("res://cn.samir.online/" + R.mipmap.pgc_default_avatar));
        tvVersion.setText("Version " + PhoneUtil.getVersionName(getContext()) + "." + PhoneUtil.getVersionCode(getContext()));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home_profile;
    }

    public static HomeProfileFragment newInstance() {
        return new HomeProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profilePresenter = new HomeProfilePresenter(new BaseHttpHandler());
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);


        items.add(new ProfileItem(getString(R.string.text_profiles_msg), 0, R.string.text_profiles_msg));
        items.add(new ProfileItem(getString(R.string.text_profiles_follow), 0, R.string.text_profiles_follow));
        items.add(new ProfileItem(getString(R.string.text_profiles_cache), 0, R.string.text_profiles_cache));
        items.add(new ProfileItem(getString(R.string.text_profiles_reply), 0, R.string.text_profiles_reply));
        items.add(new ProfileItem(getString(R.string.text_profiles_contribute), 0, R.string.text_profiles_contribute));


        return rootView;
    }


    public static class ProfileAdapter extends BaseRecyclerAdapter<ProfileItem> {


        @Override
        public ViewHolderCreator createViewHolderCreator() {
            return new ViewHolderCreator() {
                @Override
                public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {
                    return new HomeProfileItemHolder(parent.getContext(), parent);
                }

                @Override
                public int getType(String key, BaseModel bm) {
                    return 0;
                }
            };
        }
    }


    public static class ProfileItem {

        String text;
        int hasMark;
        int id;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getHasMark() {
            return hasMark;
        }

        public void setHasMark(int hasMark) {
            this.hasMark = hasMark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ProfileItem(String text, int hasMark, int id) {
            this.text = text;
            this.hasMark = hasMark;
            this.id = id;
        }
    }
}
