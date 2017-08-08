package emerald.gfoong.mindvalleyexam.object;

/**
 * Created by FOONG on 7/8/2017.
 */

public class User {

    public static final String BOARD_USER_ID        = "id";
    public static final String BOARD_USER_NAME      = "name";
    public static final String BOARD_USER_USERNAME  = "username";
    public static final String BOARD_USER_PROFILE   = "profile_image";
    public static final String BOARD_USER_LINKS     = "links";

    private String id;
    private String name;
    private String username;
    private ProfileImage profileImage;
    private Links links;

    public User(){
        id          = null;
        name        = null;
        username    = null;
        profileImage= null;
        links       = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Links getLinks() {
        return links;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
