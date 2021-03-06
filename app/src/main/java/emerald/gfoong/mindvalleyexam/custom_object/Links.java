package emerald.gfoong.mindvalleyexam.custom_object;

/**
 * Created by FOONG on 7/8/2017.
 */

public class Links {

    public static final String BOARD_LINKS_SELF     = "self";
    public static final String BOARD_LINKS_HTML     = "html";
    public static final String BOARD_LINKS_PHOTOS   = "photos";
    public static final String BOARD_LINKS_LIKES    = "likes";
    public static final String BOARD_LINKS_DOWNLOAD = "download";

    private String selfUrl;
    private String htmlUrl;
    private String photosUrl;
    private String likesUrl;
    private String downloadUrl;

    public Links(){
        this.selfUrl    = null;
        this.htmlUrl    = null;
        this.photosUrl  = null;
        this.likesUrl   = null;
        this.downloadUrl= null;
    }

    public String getSelfUrl() {
        return selfUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public String getLikesUrl() {
        return likesUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setSelfUrl(String selfUrl) {
        this.selfUrl = selfUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public void setLikesUrl(String likesUrl) {
        this.likesUrl = likesUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
