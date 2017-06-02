package cn.samir.domains.model;

/**
 * Created by xiawei on 2017/3/10.
 */

public class BaseModel<T> {
//banner

    //videoCollectionWithCover
    protected String type;
    protected int tag;
    protected T data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseModel)) return false;

        BaseModel<?> baseModel = (BaseModel<?>) o;
        return type.equals(baseModel.type);

    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "type='" + type + '\'' +
                ", tag=" + tag +
                ", data=" + data +
                '}';
    }
}
