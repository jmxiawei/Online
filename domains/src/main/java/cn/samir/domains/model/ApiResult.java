package cn.samir.domains.model;

/**
 * Created by xiaw on 2017/3/10 0010.
 */

public class ApiResult<T> {
    /**
     * count: 18,
     * total: 0,
     * nextPageUrl: "http://baobab.kaiyanapp.com/api/v4/tabs/selected?date=1489021200000&num=2&page=2",
     * date: 1489107600000,
     * nextPublishTime: 1489150800000,
     * dialog: null
     */
    public T issueList;
    public T itemList;
    public int count;
    public int total;
    public String nextPageUrl;
    public long date;
    public long nextPublishTime;
    public Dialog dialog;
    public Object caches;

    public Object getCaches() {
        return caches;
    }

    public void setCaches(Object caches) {
        this.caches = caches;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "issueList=" + issueList +
                ", itemList=" + itemList +
                ", count=" + count +
                ", total=" + total +
                ", nextPageUrl='" + nextPageUrl + '\'' +
                ", date=" + date +
                ", nextPublishTime=" + nextPublishTime +
                ", dialog='" + dialog + '\'' +
                '}';
    }
}
