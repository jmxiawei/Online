package cn.samir.online.views;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.samir.domains.model.BaseModel;
import cn.samir.online.adapter.BaseRecyclerAdapter;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.interfaces.Markable;

/**
 * Created by xiawei on 2017/3/9.
 */

public class BottomNavigationView extends RecyclerView {

    private MenuAdapter itemCommonListAdapter;
    private LayoutManager mLayoutManager;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    private OnNavigationItemClickListener onNavigationItemClickListener;


    public void setonNavigationItemClickListener(OnNavigationItemClickListener onNavigationItemClickListener) {
        this.onNavigationItemClickListener = onNavigationItemClickListener;
        if (itemCommonListAdapter != null) {
            itemCommonListAdapter.setOnNavigationItemClickListener(onNavigationItemClickListener);
        }
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
        if (this.menuItems != null && this.menuItems.size() > 0) {
            init();
        }
    }

    public void setSelect(int position) {
        itemCommonListAdapter.selectItem(position);
    }

    public MenuItem getCurrent() {

        if (itemCommonListAdapter != null) {
            return itemCommonListAdapter.getSelect();
        }
        return null;
    }

    private void init() {

        mLayoutManager = new GridLayoutManager(getContext(), menuItems.size());
        setLayoutManager(mLayoutManager);
        setHasFixedSize(true);
        itemCommonListAdapter = new MenuAdapter();
        itemCommonListAdapter.setOnNavigationItemClickListener(onNavigationItemClickListener);
        itemCommonListAdapter.setDataList(menuItems);
        setAdapter(itemCommonListAdapter);

    }

    public static class MenuItem implements Markable, Parcelable {

        public static final String MarkKey = "MenuItemMarkKey";

        public int id;
        public int normalResId;
        public int selectedResId;
        public String text;
        private boolean select;

        private boolean hasMark;


        public boolean isHasMark() {
            return hasMark;
        }

        public void setHasMark(boolean hasMark) {
            this.hasMark = hasMark;
        }

        public int getNormalResId() {
            return normalResId;
        }

        public void setNormalResId(int normalResId) {
            this.normalResId = normalResId;
        }

        public int getSelectedResId() {
            return selectedResId;
        }

        public void setSelectedResId(int selectedResId) {
            this.selectedResId = selectedResId;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public MenuItem(int id, int normalResId, int selectedResId, String text, boolean select) {
            this.id = id;
            this.normalResId = normalResId;
            this.selectedResId = selectedResId;
            this.text = text;
            this.select = select;
        }

        public MenuItem(int id, int normalResId, int selectedResId, String text) {
            this.id = id;
            this.normalResId = normalResId;
            this.selectedResId = selectedResId;
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public void onMark(String key, int count) {
            if (MarkKey.endsWith(key)) {
                setSelect(count != 0);
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.normalResId);
            dest.writeInt(this.selectedResId);
            dest.writeString(this.text);
            dest.writeByte(select ? (byte) 1 : (byte) 0);
        }

        protected MenuItem(Parcel in) {
            this.id = in.readInt();
            this.normalResId = in.readInt();
            this.selectedResId = in.readInt();
            this.text = in.readString();
            this.select = in.readByte() != 0;
        }

        public static final Parcelable.Creator<MenuItem> CREATOR = new Parcelable.Creator<MenuItem>() {
            public MenuItem createFromParcel(Parcel source) {
                return new MenuItem(source);
            }

            public MenuItem[] newArray(int size) {
                return new MenuItem[size];
            }
        };
    }


    public static class MenuAdapter extends BaseRecyclerAdapter<MenuItem> {

        private OnNavigationItemClickListener onNavigationItemClickListener;
        private int select;

        @Override
        public ViewHolderCreator createViewHolderCreator() {
            return new ViewHolderCreator() {
                @Override
                public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {
                    return new MenuHolder(parent.getContext());
                }

                @Override
                public int getType(String key, BaseModel bm) {
                    return 0;
                }
            };
        }

        public void setOnNavigationItemClickListener(OnNavigationItemClickListener oNavigationItemClickListener) {
            this.onNavigationItemClickListener = oNavigationItemClickListener;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, final int position) {
            super.onBindViewHolder(holder, position);
            ((MenuHolder) holder).itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectItem(position);
                    if (onNavigationItemClickListener != null) {
                        onNavigationItemClickListener.onItemClick(dataList.get(position), v);
                    }
                }
            });
        }

        public MenuItem getSelect() {
            return getItem(select);
        }


        public void selectItem(int position) {

            int size = getItemCount();
            select = position;
            for (int i = 0; i < size; i++) {
                MenuItem item = getItem(i);
                if (i == position) {
                    item.setSelect(true);
                } else {
                    item.setSelect(false);
                }
            }

            notifyDataSetChanged();
        }
    }


    public static class MenuHolder extends CommonViewHolder<MenuItem> {

        public MenuHolder(Context context) {
            super(new NavigationMenuView(context));
        }

        @Override
        public void bindData(final MenuItem menuItem, int position) {
            NavigationMenuView menuView = (NavigationMenuView) itemView;
            menuView.setMenuItem(menuItem);
        }
    }


    public interface OnNavigationItemClickListener {
        void onItemClick(MenuItem item, View view);
    }

}
