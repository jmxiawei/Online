package cn.samir.online.adapter;

import cn.samir.domains.model.BaseModel;

/**
 * 无线循环的aapter
 * Created by xiaw on 2017/4/14 0014.
 */

public abstract class BaseLoopRecyclerAdapter extends BaseRecyclerAdapter<BaseModel> {


    @Override
    public int getItemViewType(int position) {
        BaseModel bm = getItem(position);
        return viewHolderCreator.getType(bm.getType(), bm);
    }

    @Override
    public BaseModel getItem(int position) {
        return super.getItem(position % dataList.size());
    }


    public int getDataSize() {
        return dataList.size();
    }

    @Override
    public int getItemCount() {

        int count = dataList.size();
        if (count > 1) {
            return Integer.MAX_VALUE;
        }
        return count;
    }
}
