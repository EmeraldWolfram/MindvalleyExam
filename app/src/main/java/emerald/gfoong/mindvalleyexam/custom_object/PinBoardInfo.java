package emerald.gfoong.mindvalleyexam.custom_object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FOONG on 7/8/2017.
 */

public class PinBoardInfo {

    public static final String BOARD_ID             = "id";
    public static final String BOARD_DATE           = "created_at";
    public static final String BOARD_WIDTH          = "width";
    public static final String BOARD_HEIGHT         = "height";
    public static final String BOARD_COLOR          = "color";
    public static final String BOARD_LIKES          = "likes";
    public static final String BOARD_LIKED_BY_USR   = "liked_by_user";
    public static final String BOARD_USER           = "user";
    public static final String BOARD_COLLECTION     = "current_user_collections";
    public static final String BOARD_URLS           = "urls";
    public static final String BOARD_CATEGORIES     = "categories";
    public static final String BOARD_LINKS          = "links";

    private String id;
    private String createdDate;
    private Integer width;
    private Integer height;
    private Integer color;
    private Integer likes;
    private Boolean likedByUser;
    private User user;
    private List<Object> curUserCollections;
    private Urls urls;
    private List<CategoryItem> categoryItemList;
    private Links links;

    public PinBoardInfo(){
        curUserCollections  = new ArrayList<>();
        categoryItemList    = new ArrayList<>();
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public Integer getColor() {
        return color;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getWidth() {
        return width;
    }

    public Links getLinks() {
        return links;
    }

    public List<CategoryItem> getCategoryItemList() {
        return categoryItemList;
    }

    public List<Object> getCurUserCollections() {
        return curUserCollections;
    }

    public String getId() {
        return id;
    }

    public Urls getUrls() {
        return urls;
    }

    public User getUser() {
        return user;
    }

    public void setCategoryItemList(List<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color  = Integer.parseInt(color.substring(1), 16);
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setCurUserCollections(List<Object> curUserCollections) {
        this.curUserCollections = curUserCollections;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
