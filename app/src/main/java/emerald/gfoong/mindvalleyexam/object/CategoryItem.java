package emerald.gfoong.mindvalleyexam.object;

/**
 * Created by FOONG on 7/8/2017.
 */

public class CategoryItem {

    public static final String BOARD_CATEGORY_ID    = "id";
    public static final String BOARD_CATEGORY_TITLE = "title";
    public static final String BOARD_CATEGORY_COUNT = "photo_count";
    public static final String BOARD_CATEGORY_LINKS = "links";

    private Integer itemId;
    private String itemTitle;
    private Integer itemPhotoCount;
    private Links itemLinks;

    public CategoryItem(){
        this.itemId     = null;
        this.itemTitle  = null;
        this.itemPhotoCount = null;
        this.itemLinks  = null;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getItemPhotoCount() {
        return itemPhotoCount;
    }

    public Links getItemLinks() {
        return itemLinks;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setItemLinks(Links itemLinks) {
        this.itemLinks = itemLinks;
    }

    public void setItemPhotoCount(Integer itemPhotoCount) {
        this.itemPhotoCount = itemPhotoCount;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }
}
