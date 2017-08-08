package emerald.gfoong.mindvalleyexam.custom_view;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerald.gfoong.mindvalleyexam.R;

/**
 * Created by FOONG on 8/8/2017.
 */

public class UserViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.item_image)
    ImageView userPhoto;
    @BindView(R.id.item_text)
    TextView userName;

    public UserViewHolder(View parent){
        super(parent);
        ButterKnife.bind(this, parent);
    }

    public void setUserName(String username) {
        this.userName.setText(username);
    }

    public void setUserPhoto(Bitmap userImg) {
        this.userPhoto.setImageBitmap(userImg);
    }

}
