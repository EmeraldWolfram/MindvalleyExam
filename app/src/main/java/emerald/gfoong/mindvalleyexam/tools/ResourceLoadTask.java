package emerald.gfoong.mindvalleyexam.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import emerald.gfoong.mindvalleyexam.intf.ResourceLoadable;

/**
 * Created by FOONG on 7/8/2017.
 */

public class ResourceLoadTask extends AsyncTask<String, Void, Object> {
    private ResourceLoadable loader;

    public ResourceLoadTask(ResourceLoadable loader) {
        this.loader = loader;
    }

    protected Object doInBackground(String... urls) {
        String url = urls[0];
        BufferedInputStream inputStream;
        Object obj = null;

        try {
            URL myUrl = new URL(url);
            HttpURLConnection urlConnection = (HttpURLConnection) myUrl.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.connect();
            inputStream = new BufferedInputStream(new java.net.URL(url).openStream());
            obj = loader.decodeRes(inputStream);
            //inputStream = new java.net.URL(url).openStream();
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
}