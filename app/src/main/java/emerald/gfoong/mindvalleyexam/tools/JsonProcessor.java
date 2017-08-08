package emerald.gfoong.mindvalleyexam.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import emerald.gfoong.mindvalleyexam.object.CategoryItem;
import emerald.gfoong.mindvalleyexam.object.Links;
import emerald.gfoong.mindvalleyexam.object.PinBoardInfo;
import emerald.gfoong.mindvalleyexam.object.ProfileImage;
import emerald.gfoong.mindvalleyexam.object.Urls;
import emerald.gfoong.mindvalleyexam.object.User;

/**
 * Created by FOONG on 8/8/2017.
 *
 * JsonProcessor is a class that store all static method that parse and format JSON string
 *
 *
 */

public class JsonProcessor {

    public static List<PinBoardInfo> parsePinBoardInformation(String jsonStr) throws Exception{
        List<PinBoardInfo> boardInfoList    = new ArrayList<>();

        try{
            JSONArray pinArr    = new JSONArray(jsonStr);

            for(int i = 0; i < pinArr.length(); i++){
                JSONObject jsonPin  = pinArr.getJSONObject(i);
                PinBoardInfo pinInfo= new PinBoardInfo();

                pinInfo.setId(jsonPin.getString(PinBoardInfo.BOARD_ID));
                pinInfo.setCreatedDate(jsonPin.getString(PinBoardInfo.BOARD_DATE));
                pinInfo.setWidth(jsonPin.getInt(PinBoardInfo.BOARD_WIDTH));
                pinInfo.setHeight(jsonPin.getInt(PinBoardInfo.BOARD_HEIGHT));
                pinInfo.setColor(jsonPin.getString(PinBoardInfo.BOARD_COLOR));
                pinInfo.setLikes(jsonPin.getInt(PinBoardInfo.BOARD_LIKES));
                pinInfo.setLikedByUser(jsonPin.getBoolean(PinBoardInfo.BOARD_LIKED_BY_USR));

                JSONObject jsonUser = jsonPin.getJSONObject(PinBoardInfo.BOARD_USER);
                User user   = new User();
                user.setId(jsonUser.getString(User.BOARD_USER_ID));
                user.setName(jsonUser.getString(User.BOARD_USER_NAME));
                user.setUsername(jsonUser.getString(User.BOARD_USER_USERNAME));

                JSONObject jsonUsrProfile   = jsonUser.getJSONObject(User.BOARD_USER_PROFILE);
                ProfileImage userProfile    = new ProfileImage();
                userProfile.setSmallImgUrl(jsonUsrProfile.getString(ProfileImage.BOARD_PROFILE_SMALL));
                userProfile.setMediumImgUrl(jsonUsrProfile.getString(ProfileImage.BOARD_PROFILE_MEDIUM));
                userProfile.setLargeImgUrl(jsonUsrProfile.getString(ProfileImage.BOARD_PROFILE_LARGE));
                user.setProfileImage(userProfile);

                JSONObject jsonUsrLinks     = jsonUser.getJSONObject(User.BOARD_USER_LINKS);
                Links userLinks             = new Links();
                userLinks.setSelfUrl(jsonUsrLinks.getString(Links.BOARD_LINKS_SELF));
                userLinks.setHtmlUrl(jsonUsrLinks.getString(Links.BOARD_LINKS_HTML));
                userLinks.setLikesUrl(jsonUsrLinks.getString(Links.BOARD_LINKS_LIKES));
                userLinks.setPhotosUrl(jsonUsrLinks.getString(Links.BOARD_LINKS_PHOTOS));
                user.setLinks(userLinks);

                pinInfo.setUser(user);

                JSONArray jsonCollections   = jsonPin.getJSONArray(PinBoardInfo.BOARD_COLLECTION);
                List<Object> collection     = new ArrayList<>();
                for(int j = 0; j < jsonCollections.length(); j++){
                    collection.add(jsonCollections.get(j));
                }

                pinInfo.setCurUserCollections(collection);

                JSONObject jsonUrls = jsonPin.getJSONObject(PinBoardInfo.BOARD_URLS);
                Urls urls   = new Urls();
                urls.setRaw(jsonUrls.getString(Urls.BOARD_URLS_RAW));
                urls.setFull(jsonUrls.getString(Urls.BOARD_URLS_FULL));
                urls.setRegular(jsonUrls.getString(Urls.BOARD_URLS_REGULAR));
                urls.setSmall(jsonUrls.getString(Urls.BOARD_URLS_SMALL));
                urls.setThumb(jsonUrls.getString(Urls.BOARD_URLS_THUMB));

                pinInfo.setUrls(urls);

                JSONArray jsonCategories    = jsonPin.getJSONArray(PinBoardInfo.BOARD_CATEGORIES);
                List<CategoryItem> categoryItems    = new ArrayList<>();
                for(int k = 0; k < jsonCategories.length(); k++){
                    JSONObject jsonCatItem  = jsonCategories.getJSONObject(k);
                    CategoryItem item   = new CategoryItem();
                    item.setItemId(jsonCatItem.getInt(CategoryItem.BOARD_CATEGORY_ID));
                    item.setItemTitle(jsonCatItem.getString(CategoryItem.BOARD_CATEGORY_TITLE));
                    item.setItemPhotoCount(jsonCatItem.getInt(CategoryItem.BOARD_CATEGORY_COUNT));

                    JSONObject jsonCatLinks = jsonCatItem.getJSONObject(CategoryItem.BOARD_CATEGORY_LINKS);
                    Links catLinks  = new Links();
                    catLinks.setSelfUrl(jsonCatLinks.getString(Links.BOARD_LINKS_SELF));
                    catLinks.setPhotosUrl(jsonCatLinks.getString(Links.BOARD_LINKS_PHOTOS));

                    item.setItemLinks(catLinks);

                    categoryItems.add(item);
                }

                pinInfo.setCategoryItemList(categoryItems);

                JSONObject jsonLinks    = jsonPin.getJSONObject(PinBoardInfo.BOARD_LINKS);
                Links links = new Links();
                links.setSelfUrl(jsonLinks.getString(Links.BOARD_LINKS_SELF));
                links.setHtmlUrl(jsonLinks.getString(Links.BOARD_LINKS_HTML));
                links.setDownloadUrl(jsonLinks.getString(Links.BOARD_LINKS_DOWNLOAD));

                pinInfo.setLinks(links);

                boardInfoList.add(pinInfo);
            }

            return boardInfoList;
        } catch (JSONException err){
            //ToDo: Create CustomException with more meaningful msg and throw
            throw err;
        }

    }

}
