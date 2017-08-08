package emerald.gfoong.mindvalleyexam.object;

/**
 * Created by FOONG on 7/8/2017.
 */

public class Urls {

    public static final String BOARD_URLS_RAW       = "raw";
    public static final String BOARD_URLS_FULL      = "full";
    public static final String BOARD_URLS_REGULAR   = "regular";
    public static final String BOARD_URLS_SMALL     = "small";
    public static final String BOARD_URLS_THUMB     = "thumb";

    private String raw;
    private String full;
    private String regular;
    private String small;
    private String thumb;

    public Urls() {
        raw     = null;
        full    = null;
        regular = null;
        small   = null;
        thumb   = null;
    }

    public String getFull() {
        return full;
    }

    public String getRaw() {
        return raw;
    }

    public String getRegular() {
        return regular;
    }

    public String getSmall() {
        return small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}

