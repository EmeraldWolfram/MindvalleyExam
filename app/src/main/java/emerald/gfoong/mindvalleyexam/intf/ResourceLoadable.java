package emerald.gfoong.mindvalleyexam.intf;

import java.io.BufferedInputStream;

/**
 * Created by FOONG on 7/8/2017.
 *
 * Interface intended to be use by the ResourceLoadTask.
 * User should define a class object that implement this interface
 * and pass into the ResourceLoadTask to use
 *
 */

public interface ResourceLoadable {

    /**
     * decodeRes()
     *
     * This method is used to decode the InputStream into the resource type desired by the user
     */
    Object decodeRes(BufferedInputStream inputStream) throws Exception;

    /**
     * onDownloadCompleted()
     *
     * This method is used when the download operation completed.
     * User should implement this method to handle what to do with the downloaded resources.
     */
    void onDownloadCompleted(Object result);

    /**
     * onDownloadFailed()
     *
     * This method is used when the download operation failed.
     * User should implement this method to handle the failure
     */
    void onDownloadFailed(Exception exception);
}
