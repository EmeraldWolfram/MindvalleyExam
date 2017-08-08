package emerald.gfoong.mindvalleyexam.object;

/**
 * Created by FOONG on 7/8/2017.
 */

public class ProfileImage {

    public static final String BOARD_PROFILE_SMALL  = "small";
    public static final String BOARD_PROFILE_MEDIUM = "medium";
    public static final String BOARD_PROFILE_LARGE  = "large";

    private String smallImgUrl;
    private String mediumImgUrl;
    private String largeImgUrl;

    public ProfileImage(){
        smallImgUrl = null;
        mediumImgUrl= null;
        largeImgUrl = null;
    }

    public String getLargeImgUrl() {
        return largeImgUrl;
    }

    public String getMediumImgUrl() {
        return mediumImgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setLargeImgUrl(String largeImgUrl) {
        this.largeImgUrl = largeImgUrl;
    }

    public void setMediumImgUrl(String mediumImgUrl) {
        this.mediumImgUrl = mediumImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }
}
