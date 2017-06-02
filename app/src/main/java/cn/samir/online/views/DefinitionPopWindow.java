package cn.samir.online.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.samir.domains.model.Playinfo;
import cn.samir.domains.model.Video;
import cn.samir.online.R;

/**
 * Created by xiaw on 2017/5/19 0019.
 */

public class DefinitionPopWindow extends ListPopupWindow {

    private DefinitionAdapter adapter;

    public DefinitionPopWindow(@NonNull Context context) {
        super(context);
        adapter = new DefinitionAdapter();
        setAdapter(adapter);

    }


    public void setData(Video video) {
        if (video == null) {
            return;
        }
        adapter.setData(video.getPlayInfo());
    }

    public void show(View view) {
        setAnchorView(view);
        show();
    }

    private static final class DefinitionAdapter extends BaseAdapter {

        private List<Playinfo> playInfo = new ArrayList<>();

        @Override
        public int getCount() {
            return playInfo.size();
        }

        public void setData(List<Playinfo> playInfo) {
            this.playInfo.clear();
            this.playInfo.addAll(playInfo);
            notifyDataSetChanged();
        }

        @Override
        public Object getItem(int position) {
            return playInfo.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_video_definitions, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv_definition);
            Playinfo playinfo = (Playinfo) getItem(position);
            tv.setText(playinfo.getName());
            return convertView;
        }
    }


}
