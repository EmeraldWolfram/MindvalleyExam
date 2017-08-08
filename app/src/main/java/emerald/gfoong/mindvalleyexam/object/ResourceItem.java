package emerald.gfoong.mindvalleyexam.object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FOONG on 7/8/2017.
 */

public class ResourceItem {

    private String id;
    private Integer createdDate;
    private Integer width;
    private Integer height;
    private Integer color;
    private Integer likes;
    private Boolean likedByUser;
    private User user;
    private List<String> curUserCollections;
    private Urls urls;
    private List<CategoryItem> categoryItemList;
    private Links links;

    public ResourceItem(){
        curUserCollections  = new ArrayList<>();
        categoryItemList    = new ArrayList<>();
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public Integer getColor() {
        return color;
    }

    public Integer getCreatedDate() {
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

    public List<String> getCurUserCollections() {
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

    public void setCreatedDate(Integer createdDate) {
        this.createdDate = createdDate;
    }

    public void setCurUserCollections(List<String> curUserCollections) {
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
