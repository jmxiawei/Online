package cn.samir.online.ui.video;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.ApiResult;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.Video;
import cn.samir.online.R;
import cn.samir.online.adapter.ItemSettings;
import cn.samir.online.http.ApiService;
import cn.samir.online.http.converts.TypeProvider;
import cn.samir.online.ui.fragment.RecyclerFragment;

/**
 * Created by xiaw on 2017/4/20 0020.
 */

public class RelativeVideoFragment extends RecyclerFragment {


    @BindView(R.id.sdv_background)
    SimpleDraweeView sdvBackground;
    private Video mVideo = null;


    private BaseModel<Video> mode4Header;

    public static RelativeVideoFragment newInstance(Video video) {

        Bundle bundle = new Bundle();
        bundle.putParcelable(Video.NAME, video);
        bundle.putString(PARAM_URL, ApiService.relativeUrl + "?id=" + video.getId());
        RelativeVideoFragment r = new RelativeVideoFragment();
        ItemSettings settings = new ItemSettings(ItemSettings.BACKGROUND_DARK);
        settings.setNeedWhiteBackground(false);
        r.setItemSettings(settings);
        r.setArguments(bundle);
        return r;
    }


    @Override
    public void onEvent(Object data) {

//       if (data instanceof Video) {
//            mVideo = (Video) data;
//            onSetVideo();
//        }
    }

    private void onSetVideo() {
        loadUrl(ApiService.relativeUrl + "?id=" + mVideo.getId());
        sdvBackground.setImageURI(Uri.parse(mVideo.getCover().getBlurred()));
        mode4Header.setData(mVideo);
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_relative_video;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sdvBackground.setImageURI(Uri.parse(mVideo.getCover().getBlurred()));
    }


    @Override
    public boolean onSuccess(Object... params) {

        if (dataRecyclerViewAdapter.getItemCount() == 0) {
            ApiResult<ArrayList<BaseModel>> apiResult = (ApiResult<ArrayList<BaseModel>>) params[0];
            apiResult.itemList.add(0, mode4Header);
        }

        return super.onSuccess(params);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mVideo = arguments.getParcelable(Video.NAME);
        }
        if (mVideo == null) {
            throw new NullPointerException("mVideo == NULL");
        }
        mode4Header = new BaseModel<>();
        mode4Header.setData(mVideo);
        mode4Header.setType(TypeProvider.CUSTOM_VIDEO_DETAIL_HEADER);
        return rootView;
    }
}
