package cn.samir.online.http.converts;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.dinuscxj.pullzoom.PullZoomRecyclerView;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import cn.samir.domains.model.ActionCard;
import cn.samir.domains.model.Banner;
import cn.samir.domains.model.BaseModel;
import cn.samir.domains.model.BlankCard;
import cn.samir.domains.model.BriefCard;
import cn.samir.domains.model.HorizontalScrollCard;
import cn.samir.domains.model.IssueNavigationCard;
import cn.samir.domains.model.ItemCollection;
import cn.samir.domains.model.LeftAlignTextHeader;
import cn.samir.domains.model.RectangleCard;
import cn.samir.domains.model.SquareCard;
import cn.samir.domains.model.TextCard;
import cn.samir.domains.model.TextFooter;
import cn.samir.domains.model.TextHeader;
import cn.samir.domains.model.Video;
import cn.samir.online.adapter.holders.ActionCardHolder;
import cn.samir.online.adapter.holders.BannerCollectionHolder;
import cn.samir.online.adapter.holders.BannerHolder;
import cn.samir.online.adapter.holders.BlankCardHolder;
import cn.samir.online.adapter.holders.BriefCardHolder;
import cn.samir.online.adapter.holders.HeaderViewHolder;
import cn.samir.online.adapter.holders.HorizontalScrollCardHolder;
import cn.samir.online.adapter.holders.IssueNavigationCardHolder;
import cn.samir.online.adapter.holders.LeftAlignTextHeaderHolder;
import cn.samir.online.adapter.holders.LoadMoreHolder;
import cn.samir.online.adapter.holders.MaskToolBarHolder;
import cn.samir.online.adapter.holders.NoMoreHolder;
import cn.samir.online.adapter.holders.RectangleCardHolder;
import cn.samir.online.adapter.holders.SquareCardCollectionHolder;
import cn.samir.online.adapter.holders.SquareCardHolder;
import cn.samir.online.adapter.holders.TextCardHeader1Footer1Holder;
import cn.samir.online.adapter.holders.TextCardHeader2Holder;
import cn.samir.online.adapter.holders.TextCardHeader3Holder;
import cn.samir.online.adapter.holders.TextCardHeader4Holder;
import cn.samir.online.adapter.holders.TextFooterHolder;
import cn.samir.online.adapter.holders.TextHeaderHolder;
import cn.samir.online.adapter.holders.VideoCollectionOfAuthorWithCoverHolder;
import cn.samir.online.adapter.holders.VideoCollectionOfFollowHolder;
import cn.samir.online.adapter.holders.VideoCollectionOfHorizontalScrollCardHolder;
import cn.samir.online.adapter.holders.VideoCollectionWithBriefHolder;
import cn.samir.online.adapter.holders.VideoCollectionWithCoverHolder;
import cn.samir.online.adapter.holders.VideoDetailHeaderHolder;
import cn.samir.online.adapter.holders.VideoHolder;
import cn.samir.online.adapter.holders.VideoSmallCardHolder;
import cn.samir.online.adapter.holders.base.CommonViewHolder;
import cn.samir.online.views.HeaderZoomView;

/**
 * 由於界面的所有顯示都來自於数据结构
 * 项目中所有的数据类都在这里了
 *  每个类型对应一个holder
 * Created by xiaw on 2017/4/7 0007.
 */

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class TypeProvider {

    private static TypeProvider instance = null;
    private static HashMap<String, TypeDataItem> keyDataItemHashMap = new HashMap<>();
    private static SparseArray<TypeDataItem> typeDataItemHashMap = new SparseArray<>();


    public static final int TYPE_INVALID = Integer.MAX_VALUE >> 2;

    public static final int TYPE_BANNER2 = 0;
    public static final int TYPE_TEXT_HEADER = 1;
    public static final int TYPE_TEXT_FOOTER = 2;
    public static final int TYPE_VIDEO = 3;
    public static final int TYPE_VideoCollectionOfFollow = 4;
    public static final int TYPE_HEADER = 5;
    public static final int TYPE_VideoCollectionWithCover = 6;
    public static final int TYPE_horizontalScrollCard = 7;
    public static final int TYPE_BANNER = 8;
    public static final int TYPE_TEXT_CARD = 9;
    public static final int TYPE_squareCardCollection = 10;
    public static final int TYPE_bannerCollection = 11;
    public static final int TYPE_videoCollectionOfHorizontalScrollCard = 12;

    public static final int TYPE_leftAlignTextHeader = 13;
    public static final int TYPE_briefCard = 14;
    public static final int TYPE_blankCard = 15;
    public static final int TYPE_videoCollectionWithBrief = 16;
    public static final int TYPE_squareCard = 17;
    public static final int TYPE_actionCard = 18;
    public static final int TYPE_TEXT_CARD_header1 = 19;
    public static final int TYPE_TEXT_CARD_header2 = 20;
    public static final int TYPE_TEXT_CARD_header3 = 21;
    public static final int TYPE_TEXT_CARD_footer1 = 22;
    public static final int TYPE_TEXT_CARD_header4 = 23;
    public static final int TYPE_LOAD_MORE_FOOTER = 24;
    public static final int TYPE_videoCollectionOfAuthorWithCover = 25;
    public static final int TYPE_LOAD_NO_MORE_FOOTER = 26;
    public static final int TYPE_videoSmallCard = 27;

    public static final int TYPE_CUSTOM_VIDEO_DETAIL_HEADER = 28;

    public static final int TYPE_issueNavigationCard = 29;
    public static final int TYPE_rectangleCard = 30;
    public static final int TYPE_maskToolbar = 31;

    public static final String maskToolbar = "maskToolbar";

    public static final String rectangleCard = "rectangleCard";
    public static final String issueNavigationCard = "issueNavigationCard";
    public static final String CUSTOM_VIDEO_DETAIL_HEADER = "CUSTOM_VIDEO_DETAIL_HEADER";
    public static final String videoSmallCard = "videoSmallCard";
    public static final String LOAD_MORE_FOOTER = "LOAD_MORE_FOOTER";
    public static final String LOAD_NO_MORE_FOOTER = "LOAD_NO_MORE_FOOTER";
    public static final String PULL_HEADER = "PULL_HEADER";
    public static final String BANNER2 = "banner2";
    public static final String BANNER = "banner";
    public static final String VIDEO = "video";
    public static final String TEXT_CARD = "textCard";
    public static final String TEXT_CARD_header1 = "textCard_header1";
    public static final String TEXT_CARD_header2 = "textCard_header2";
    public static final String TEXT_CARD_header3 = "textCard_header3";
    public static final String TEXT_CARD_header4 = "textCard_header4";
    public static final String TEXT_CARD_footer1 = "textCard_footer1";


    public static final String TEXTFOOTER = "textFooter";
    public static final String TEXTHEADER = "textHeader";
    public static final String videoCollectionOfFollow = "videoCollectionOfFollow";
    public static final String videoCollectionWithCover = "videoCollectionWithCover";
    public static final String horizontalScrollCard = "horizontalScrollCard";
    public static final String squareCardCollection = "squareCardCollection";
    public static final String bannerCollection = "bannerCollection";
    public static final String videoCollectionOfHorizontalScrollCard = "videoCollectionOfHorizontalScrollCard";


    public static final String leftAlignTextHeader = "leftAlignTextHeader";
    public static final String briefCard = "briefCard";
    public static final String blankCard = "blankCard";
    public static final String videoCollectionWithBrief = "videoCollectionWithBrief";
    public static final String actionCard = "actionCard";
    public static final String squareCard = "squareCard";
    public static final String videoCollectionOfAuthorWithCover = "videoCollectionOfAuthorWithCover";


    private TypeProvider() {

        registerType(maskToolbar, new TypeDataItem(maskToolbar, TYPE_maskToolbar, new TypeToken<BaseModel<String>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new MaskToolBarHolder(parent.getContext(), parent);
            }
        });

        registerType(rectangleCard, new TypeDataItem(rectangleCard, TYPE_rectangleCard, new TypeToken<BaseModel<RectangleCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new RectangleCardHolder(parent.getContext(), parent);
            }
        });


        registerType(issueNavigationCard, new TypeDataItem(issueNavigationCard, TYPE_issueNavigationCard, new TypeToken<BaseModel<IssueNavigationCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new IssueNavigationCardHolder(parent.getContext(), parent);
            }
        });

        registerType(CUSTOM_VIDEO_DETAIL_HEADER, new TypeDataItem(CUSTOM_VIDEO_DETAIL_HEADER, TYPE_CUSTOM_VIDEO_DETAIL_HEADER, new TypeToken<BaseModel<Video>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoDetailHeaderHolder(parent.getContext(), parent);
            }
        });


        registerType(videoSmallCard, new TypeDataItem(videoSmallCard, TYPE_videoSmallCard, new TypeToken<BaseModel<Video>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoSmallCardHolder(parent.getContext(), parent);
            }
        });


        registerType(videoCollectionOfAuthorWithCover, new TypeDataItem(videoCollectionOfAuthorWithCover, TYPE_videoCollectionOfAuthorWithCover, new TypeToken<BaseModel<ItemCollection<BaseModel>>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoCollectionOfAuthorWithCoverHolder(parent.getContext(), parent);
            }
        });

        registerType(LOAD_NO_MORE_FOOTER, new TypeDataItem(LOAD_NO_MORE_FOOTER, TYPE_LOAD_NO_MORE_FOOTER, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new NoMoreHolder(parent.getContext(), parent);
            }
        });


        registerType(LOAD_MORE_FOOTER, new TypeDataItem(LOAD_MORE_FOOTER, TYPE_LOAD_MORE_FOOTER, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new LoadMoreHolder(parent.getContext(), parent);
            }
        });


        registerType(TEXT_CARD, new TypeDataItem(TEXT_CARD, TYPE_TEXT_CARD, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextCardHeader1Footer1Holder(parent.getContext(), parent);
            }
        });


        registerType(TEXT_CARD_header1, new TypeDataItem(TEXT_CARD_header1, TYPE_TEXT_CARD_header1, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextCardHeader1Footer1Holder(parent.getContext(), parent);
            }
        });

        registerType(TEXT_CARD_footer1, new TypeDataItem(TEXT_CARD_footer1, TYPE_TEXT_CARD_footer1, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextCardHeader1Footer1Holder(parent.getContext(), parent);
            }
        });


        registerType(TEXT_CARD_header2, new TypeDataItem(TEXT_CARD_header2, TYPE_TEXT_CARD_header2, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextCardHeader2Holder(parent.getContext(), parent);
            }
        });

        registerType(TEXT_CARD_header3, new TypeDataItem(TEXT_CARD_header3, TYPE_TEXT_CARD_header3, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextCardHeader3Holder(parent.getContext(), parent);
            }
        });


        registerType(TEXT_CARD_header4, new TypeDataItem(TEXT_CARD_header4, TYPE_TEXT_CARD_header4, new TypeToken<BaseModel<TextCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextCardHeader4Holder(parent.getContext(), parent);
            }
        });


        registerType(PULL_HEADER, new TypeDataItem(PULL_HEADER, TYPE_HEADER, new TypeToken<BaseModel>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new HeaderViewHolder(new HeaderZoomView(parent.getContext()), (PullZoomRecyclerView) p[0]);
            }
        });

        registerType(BANNER, new TypeDataItem(BANNER, TYPE_BANNER, new TypeToken<BaseModel<Banner>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new BannerHolder(parent.getContext(), parent);
            }
        });


        registerType(BANNER2, new TypeDataItem(BANNER2, TYPE_BANNER2, new TypeToken<BaseModel<Banner>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new BannerHolder(parent.getContext(), parent);
            }
        });


        registerType(TEXTHEADER, new TypeDataItem(TEXTHEADER, TYPE_TEXT_HEADER, new TypeToken<BaseModel<TextHeader>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextHeaderHolder(parent.getContext(), parent);
            }
        });

        registerType(TEXTFOOTER, new TypeDataItem(TEXTFOOTER, TYPE_TEXT_FOOTER, new TypeToken<BaseModel<TextFooter>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new TextFooterHolder(parent.getContext(), parent);
            }
        });

        registerType(VIDEO, new TypeDataItem(VIDEO, TYPE_VIDEO, new TypeToken<BaseModel<Video>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoHolder(parent.getContext(), parent);
            }
        });

        registerType(videoCollectionOfFollow, new TypeDataItem(videoCollectionOfFollow, TYPE_VideoCollectionOfFollow, new TypeToken<BaseModel<ItemCollection<BaseModel>>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoCollectionOfFollowHolder(parent.getContext(), parent);
            }
        });

        registerType(videoCollectionWithCover, new TypeDataItem(videoCollectionWithCover, TYPE_VideoCollectionWithCover, new TypeToken<BaseModel<ItemCollection<BaseModel>>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoCollectionWithCoverHolder(parent.getContext(), parent);
            }
        });

        registerType(horizontalScrollCard, new TypeDataItem(horizontalScrollCard, TYPE_horizontalScrollCard, new TypeToken<BaseModel<HorizontalScrollCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new HorizontalScrollCardHolder(parent.getContext(), parent);
            }
        });


        registerType(squareCardCollection, new TypeDataItem(squareCardCollection, TYPE_squareCardCollection, new TypeToken<BaseModel<ItemCollection<BaseModel>>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new SquareCardCollectionHolder(parent.getContext(), parent);
            }
        });

        registerType(bannerCollection, new TypeDataItem(bannerCollection, TYPE_bannerCollection, new TypeToken<BaseModel<ItemCollection<BaseModel>>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new BannerCollectionHolder(parent.getContext(), parent);
            }
        });

        registerType(videoCollectionOfHorizontalScrollCard, new TypeDataItem(videoCollectionOfHorizontalScrollCard,
                TYPE_videoCollectionOfHorizontalScrollCard, new TypeToken<BaseModel<ItemCollection<BaseModel>>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoCollectionOfHorizontalScrollCardHolder(parent.getContext(), parent);
            }
        });


        registerType(blankCard, new TypeDataItem(blankCard,
                TYPE_blankCard, new TypeToken<BaseModel<BlankCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new BlankCardHolder(parent.getContext(), parent);
            }
        });

        registerType(leftAlignTextHeader, new TypeDataItem(leftAlignTextHeader,
                TYPE_leftAlignTextHeader, new TypeToken<BaseModel<LeftAlignTextHeader>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new LeftAlignTextHeaderHolder(parent.getContext(), parent);
            }
        });


        registerType(briefCard, new TypeDataItem(briefCard,
                TYPE_briefCard, new TypeToken<BaseModel<BriefCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new BriefCardHolder(parent.getContext(), parent);
            }
        });

        registerType(videoCollectionWithBrief, new TypeDataItem(videoCollectionWithBrief,
                TYPE_videoCollectionWithBrief, new TypeToken<BaseModel<ItemCollection<BaseModel>>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new VideoCollectionWithBriefHolder(parent.getContext(), parent);
            }
        });


        registerType(actionCard, new TypeDataItem(actionCard,
                TYPE_actionCard, new TypeToken<BaseModel<ActionCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new ActionCardHolder(parent.getContext(), parent);
            }
        });
        registerType(squareCard, new TypeDataItem(squareCard,
                TYPE_squareCard, new TypeToken<BaseModel<SquareCard>>() {
        }) {
            @Override
            public CommonViewHolder getHolder(ViewGroup parent, int viewType, Object... p) {
                return new SquareCardHolder(parent.getContext(), parent);
            }
        });
    }

    /**
     * 头部有分割线
     *
     * @param type
     * @return
     */
    public boolean isHeaderCollectionHolder(String type) {

        if (type == null) {
            return false;
        }

        return type.equals(videoCollectionWithBrief)
                || type.equals(squareCardCollection)
                || type.equals(bannerCollection)
                || type.equals(videoCollectionOfHorizontalScrollCard);
    }


    public boolean isFooterCollectionHolder(String type) {
        return false;
    }


    /**
     * Regist a type for recyclerViewHolder
     *
     * @param key
     * @param item
     */
    public void registerType(String key, TypeDataItem item) {
        keyDataItemHashMap.put(key, item);
        typeDataItemHashMap.put(item.getAdapterItemType(), item);
    }

    public static TypeProvider getInstance() {

        if (instance == null) {
            synchronized (TypeProvider.class) {
                if (instance == null) {
                    instance = new TypeProvider();
                }
            }
        }

        return instance;
    }

    public TypeDataItem getByKey(String keyType) {


        return keyDataItemHashMap.get(keyType);
    }


    public TypeDataItem getByType(int keyType) {
        return typeDataItemHashMap.get(keyType);
    }

}
