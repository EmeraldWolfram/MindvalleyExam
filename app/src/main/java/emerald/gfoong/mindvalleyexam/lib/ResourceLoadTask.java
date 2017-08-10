package emerald.gfoong.mindvalleyexam.lib;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

import emerald.gfoong.mindvalleyexam.intf.ResourceLoadable;

/**
 * Created by FOONG on 7/8/2017.
 *
 * An asynchronous thread that will download any object from the internet via the url provided
 * by user.
 *
 * =============================
 * ResourceLoadTask - User Guide
 * =============================
 * Step 1: Instantiate an object that implement the ResourceLoadable interface
 * Step 2: Fill up ResourceLoadable interface with the desired job
 * Step 3: Instantiate a ResourceLoadTask object and put in the Object that implement
 *         ResourceLoadable interface
 * Step 4: Call the method resourceLoadTask.execute(url) with an argument url as the url that
 *         have the resource
 *
 * =================================================================================================
 * Eg.
 *      MyResourceLoadable myLoadable   = new MyResourceLoadable();
 *      ResourceLoadTask task           = new ResourceLoadTask(myLoadable);
 *      task.execute("http://pastebin.com/raw/wgkJgazE");
 *
 * Please refer to onBindViewHolder() method in presenter/MainPresenter.java for reference purpose.
 *
 * =================================================================================================
 * Additional:
 * User may choose to set the maximum capacity to store in the queue instead of the default 10
 */

public class ResourceLoadTask extends AsyncTask<String, Void, Object> {
    private ResourceLoadable loader;
    private static Semaphore mutex  = new Semaphore(1);
    private static HashMap<String, Object> loadedItemMap    = new HashMap<>();
    private static ArrayList<String> loadedItemKeyList      = new ArrayList<>();
    private static int maxCapacity = 10;

    public ResourceLoadTask(ResourceLoadable loader) {
        this.loader = loader;
    }

    protected Object doInBackground(String... urls) {
        String url = urls[0];
        BufferedInputStream inputStream;
        Object obj = null;

        try {
            if(isLoadedItem(url)){
                moveUrlToHigherPriorityCache(url);
                return loadedItemMap.get(url);
            } else {
                inputStream = new BufferedInputStream(new URL(url).openStream());
                obj = loader.decodeRes(inputStream);
                putUrlIntoLoadedItemList(url, obj);
            }
        } catch (Exception e) {
            loader.onDownloadFailed(e);
        }

        return obj;
    }

    @Override
    protected void onPostExecute(Object object) {
        if(object != null){
            loader.onDownloadCompleted(object);
        }
    }

    /**
     * moveUrlToHigherPriority
     *
     * This method move up the url priority when called.
     * If the priority is at the last (largest index), the url cannot be higher anymore as it was
     * the highest priority
     *
     * Eg Case:
     *      When the same url was loaded the second time, the program should keep this item longer
     *      and prevent it from being deleted as the item were used more often.
     *
     * @param url   the url to load the item.
     */
    private void moveUrlToHigherPriorityCache(String url) throws Exception{
        int position    = loadedItemKeyList.indexOf(url);
        if (position < (loadedItemKeyList.size() - 1)) {
            mutex.acquire();
            loadedItemKeyList.remove(position);
            loadedItemKeyList.add((position + 1), url);
            mutex.release();
        }
    }


    /**
     * putUrlIntoLoadedItemList
     *
     * This method will put the url into the loadedItemMap to register into the cache
     *
     * @param url   the url to load the item.
     */
    private void putUrlIntoLoadedItemList(String url, Object item) throws Exception {
        mutex.acquire();
        loadedItemMap.put(url, item);
        loadedItemKeyList.add(url);
        if (loadedItemMap.size() > maxCapacity) {
            loadedItemMap.remove(loadedItemKeyList.remove(0));
        }
        mutex.release();
    }

    /**
     * isLoadedItem
     *
     * This method check if the url was loaded before
     *
     * @param url   the url to be loaded
     */
    private boolean isLoadedItem(String url) throws Exception{
        boolean isLoaded;
        mutex.acquire();
        isLoaded = loadedItemMap.containsKey(url);
        mutex.release();

        return isLoaded;
    }

    public static void setMaxCapacity(int maxCapacity) {
        ResourceLoadTask.maxCapacity = maxCapacity;
    }
}