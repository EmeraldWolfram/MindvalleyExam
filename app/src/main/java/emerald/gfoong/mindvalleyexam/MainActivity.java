package emerald.gfoong.mindvalleyexam;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerald.gfoong.mindvalleyexam.intf.ResourceLoadable;
import emerald.gfoong.mindvalleyexam.task.ResourceLoadTask;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_img)
    ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ResourceLoadTask task = new ResourceLoadTask(new ResourceLoadable() {

            @Override
            public Object decodeRes(BufferedInputStream inputStream) {
                return BitmapFactory.decodeStream(inputStream);
            }

            @Override
            public void onDownloadCompleted(Object object) {
                Bitmap img  = (Bitmap)object;
                if(img != null) {
                   myImage.setImageBitmap(img);
                } else {
                    Toast.makeText(MainActivity.this, "Bitmap is null", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onDownloadFailed(Exception exception) {
                Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        String url = "https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5\\u0026q=80\\u0026fm=jpg\\u0026crop=faces\\u0026fit=crop\\u0026h=32\\u0026w=32\\u0026s=63f1d805cffccb834cf839c719d91702";
        task.execute(url);
    }
}
