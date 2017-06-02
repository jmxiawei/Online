package cn.samir.online.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.ParcelableString;
import cn.samir.online.R;
import cn.samir.online.adapter.BaseRecyclerAdapter;
import cn.samir.online.adapter.holders.ViewHolderCreator;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.util.PhotoUtil;
import cn.samir.online.util.Utils;

/**
 * 根据返回的数据显示dialog
 * Created by xiaw on 2017/5/31 0031.
 */

public class EyeTopImageDialog extends Dialog {


    @BindView(R.id.sdv_header)
    SimpleDraweeView sdvHeader;
    @BindView(R.id.tv_title)
    CustomFontTextView tvTitle;
    @BindView(R.id.tv_content)
    CustomFontTextView tvContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.line)
    View line;
    private cn.samir.domains.model.Dialog mDialogNode;


    private ButtonListAdapter mButtonListAdapter;

    private View contentView;

    public EyeTopImageDialog(@NonNull Context context) {
        this(context, 0);
    }

    public EyeTopImageDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }

    protected EyeTopImageDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();


    }

    private void init() {
        contentView = LayoutInflater.from(getContext()).inflate(R.layout.layout_eye_top_image_dialog, null);
        ButterKnife.bind(this, contentView);
        setContentView(contentView);


    }

    public cn.samir.domains.model.Dialog getDialogNode() {
        return mDialogNode;
    }

    public void setDialogNode(cn.samir.domains.model.Dialog mDialogNode) {
        this.mDialogNode = mDialogNode;
        PhotoUtil.showPhoto(sdvHeader, this.mDialogNode.getImage());
        tvTitle.setText(this.mDialogNode.getTitle());
        tvContent.setText(this.mDialogNode.getContent());


        if (mDialogNode.getButtonList() == null || mDialogNode.getButtonList().size() == 0) {
            //没有按钮
            line.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), mDialogNode.getButtonList().size()));
            mButtonListAdapter = new ButtonListAdapter();
            recyclerView.setAdapter(mButtonListAdapter);
            mButtonListAdapter.setDataList(mDialogNode.getButtonList());
        }

    }


    public static class ButtonListAdapter extends BaseRecyclerAdapter<cn.samir.domains.model.Dialog.ButtonListEntity> {

        @Override
        public ViewHolderCreator createViewHolderCreator() {
            return new ViewHolderCreator() {
                @Override
                public CommonViewHolder createByViewGroupAndType(ViewGroup parent, int viewType, Object... p) {
                    return new ButtonHolder(parent.getContext(), parent);
                }

                @Override
                public int getType(String key, BaseModel bm) {
                    return 0;
                }
            };
        }
    }


    public static class ButtonHolder extends CommonViewHolder<cn.samir.domains.model.Dialog.ButtonListEntity> {

        @BindView(R.id.btn_list)
        Button btnList;


        cn.samir.domains.model.Dialog.ButtonListEntity mButtonListEntity;

        public ButtonHolder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_holder_dialog_button);
        }

        @Override
        public void bindData(cn.samir.domains.model.Dialog.ButtonListEntity buttonListEntity, int position) {
            this.mButtonListEntity = buttonListEntity;


            if (this.mButtonListEntity.getHighlight()) {
                btnList.setTextColor(getContext().getResources().getColor(R.color.text_color_black));
            } else {
                btnList.setTextColor(getContext().getResources().getColor(R.color.text_color_gray));
            }
            btnList.setText(buttonListEntity.getText());

        }


        @Override
        public Parcelable getDataForBroadcast() {
            if (Utils.isEmpty(this.mButtonListEntity.getActionUrl())) {
                return null;
            }
            return new ParcelableString(this.mButtonListEntity.getActionUrl());
        }
    }


}
