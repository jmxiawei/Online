package cn.samir.domains.model;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-generated: 2017-03-10 15:43:2
 *
 * @author www.jsons.cn
 * @website http://www.jsons.cn/json2java/
 */
public class Video implements Parcelable {

    public static final String NAME = "cn.samir.online.video";

    public long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(long downloadId) {
        this.downloadId = downloadId;
    }

    private Uri downloadUri;

    private long downloadId = -1;
    private String dataType;
    private int id;
    private String title;
    private String description;
    private Provider provider;
    private String category;
    private Author author;
    private Cover cover;
    private String playUrl;
    private int duration;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;
        Video video = (Video) o;
        return id == video.id;

    }

    public Uri getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(Uri downloadUri) {
        this.downloadUri = downloadUri;
    }

    @Override
    public int hashCode() {
        return id;
    }

    private WebUrl webUrl;

    private long releaseTime;

    private List<Playinfo> playInfo;
    private Consumption consumption;
    private String campaign;

    private String waterMarks;

    private ArrayList<AdOrganizition> adTrack;
    private List<Tag> tags;
    private String type;
    private List<SubTitiles> subtitles;

    private String titlePgc;

    private String descriptionPgc;
    private int idx;

    private long date;
    private Promotion promotion;
    private Label label;

    private String descriptionEditor;
    private boolean collected;
    private boolean played;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public WebUrl getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(WebUrl webUrl) {
        this.webUrl = webUrl;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(int releaseTime) {
        this.releaseTime = releaseTime;
    }

    public List<Playinfo> getPlayInfo() {
        return playInfo;
    }

    public void setPlayInfo(List<Playinfo> playInfo) {
        this.playInfo = playInfo;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getWaterMarks() {
        return waterMarks;
    }

    public void setWaterMarks(String waterMarks) {
        this.waterMarks = waterMarks;
    }

    public ArrayList<AdOrganizition> getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(ArrayList<AdOrganizition> adTrack) {
        this.adTrack = adTrack;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SubTitiles> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<SubTitiles> subtitles) {
        this.subtitles = subtitles;
    }

    public String getTitlePgc() {
        return titlePgc;
    }

    public void setTitlePgc(String titlePgc) {
        this.titlePgc = titlePgc;
    }

    public String getDescriptionPgc() {
        return descriptionPgc;
    }

    public void setDescriptionPgc(String descriptionPgc) {
        this.descriptionPgc = descriptionPgc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getDescriptionEditor() {
        return descriptionEditor;
    }

    public void setDescriptionEditor(String descriptionEditor) {
        this.descriptionEditor = descriptionEditor;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }


    @Override
    public String toString() {
        return "Video{" +
                "downloadUri=" + downloadUri +
                ", downloadId=" + downloadId +
                ", dataType='" + dataType + '\'' +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", provider=" + provider +
                ", category='" + category + '\'' +
                ", author=" + author +
                ", cover=" + cover +
                ", playUrl='" + playUrl + '\'' +
                ", duration=" + duration +
                ", webUrl=" + webUrl +
                ", releaseTime=" + releaseTime +
                ", playInfo=" + playInfo +
                ", consumption=" + consumption +
                ", campaign='" + campaign + '\'' +
                ", waterMarks='" + waterMarks + '\'' +
                ", adTrack=" + adTrack +
                ", tags=" + tags +
                ", type='" + type + '\'' +
                ", subtitles=" + subtitles +
                ", titlePgc='" + titlePgc + '\'' +
                ", descriptionPgc='" + descriptionPgc + '\'' +
                ", idx=" + idx +
                ", date=" + date +
                ", promotion=" + promotion +
                ", label=" + label +
                ", descriptionEditor='" + descriptionEditor + '\'' +
                ", collected=" + collected +
                ", played=" + played +
                '}';
    }

    public Video() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.downloadUri, 0);
        dest.writeLong(this.downloadId);
        dest.writeString(this.dataType);
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.provider, 0);
        dest.writeString(this.category);
        dest.writeParcelable(this.author, 0);
        dest.writeParcelable(this.cover, 0);
        dest.writeString(this.playUrl);
        dest.writeInt(this.duration);
        dest.writeParcelable(this.webUrl, 0);
        dest.writeLong(this.releaseTime);
        dest.writeTypedList(playInfo);
        dest.writeParcelable(this.consumption, 0);
        dest.writeString(this.campaign);
        dest.writeString(this.waterMarks);
        dest.writeTypedList(adTrack);
        dest.writeTypedList(tags);
        dest.writeString(this.type);
        dest.writeTypedList(subtitles);
        dest.writeString(this.titlePgc);
        dest.writeString(this.descriptionPgc);
        dest.writeInt(this.idx);
        dest.writeLong(this.date);
        dest.writeParcelable(this.promotion, 0);
        dest.writeParcelable(this.label, 0);
        dest.writeString(this.descriptionEditor);
        dest.writeByte(collected ? (byte) 1 : (byte) 0);
        dest.writeByte(played ? (byte) 1 : (byte) 0);
    }

    protected Video(Parcel in) {
        this.downloadUri = in.readParcelable(Uri.class.getClassLoader());
        this.downloadId = in.readLong();
        this.dataType = in.readString();
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.provider = in.readParcelable(Provider.class.getClassLoader());
        this.category = in.readString();
        this.author = in.readParcelable(Author.class.getClassLoader());
        this.cover = in.readParcelable(Cover.class.getClassLoader());
        this.playUrl = in.readString();
        this.duration = in.readInt();
        this.webUrl = in.readParcelable(WebUrl.class.getClassLoader());
        this.releaseTime = in.readLong();
        this.playInfo = in.createTypedArrayList(Playinfo.CREATOR);
        this.consumption = in.readParcelable(Consumption.class.getClassLoader());
        this.campaign = in.readString();
        this.waterMarks = in.readString();
        this.adTrack = in.createTypedArrayList(AdOrganizition.CREATOR);
        this.tags = in.createTypedArrayList(Tag.CREATOR);
        this.type = in.readString();
        this.subtitles = in.createTypedArrayList(SubTitiles.CREATOR);
        this.titlePgc = in.readString();
        this.descriptionPgc = in.readString();
        this.idx = in.readInt();
        this.date = in.readLong();
        this.promotion = in.readParcelable(Promotion.class.getClassLoader());
        this.label = in.readParcelable(Label.class.getClassLoader());
        this.descriptionEditor = in.readString();
        this.collected = in.readByte() != 0;
        this.played = in.readByte() != 0;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}