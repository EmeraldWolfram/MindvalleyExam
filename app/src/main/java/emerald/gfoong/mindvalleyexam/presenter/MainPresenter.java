package emerald.gfoong.mindvalleyexam.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Locale;

import emerald.gfoong.mindvalleyexam.R;
import emerald.gfoong.mindvalleyexam.custom_object.ProfileImage;
import emerald.gfoong.mindvalleyexam.custom_object.User;
import emerald.gfoong.mindvalleyexam.custom_view.UserViewHolder;
import emerald.gfoong.mindvalleyexam.intf.MvpMain;
import emerald.gfoong.mindvalleyexam.intf.ResourceLoadable;
import emerald.gfoong.mindvalleyexam.lib.ButtonWrapper;
import emerald.gfoong.mindvalleyexam.lib.PopUpException;
import emerald.gfoong.mindvalleyexam.lib.ResourceLoadTask;

/**
 * Created by FOONG on 8/8/2017.
 */

public class MainPresenter implements MvpMain.MvpPresenterM, MvpMain.MvpPresenterV {

    private MvpMain.MvpView taskView;
    private MvpMain.MvpModel taskModel;

    private ArrayList<User> userList;

    private Paint p;
    private Bitmap trashIcon;
    private User tempUser;
    private int tempPosition;
    private Snackbar snackbar;

    public MainPresenter(MvpMain.MvpView taskView, Bitmap trashIcon){
        this.p          = new Paint();
        this.taskView   = taskView;
        this.userList   = new ArrayList<>();
        this.trashIcon = trashIcon;
    }

    public void setTaskModel(MvpMain.MvpModel taskModel) {
        this.taskModel = taskModel;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        User user               = userList.get(position);
        String username         = user.getUsername();
        ProfileImage profileImg = user.getProfileImage();

        holder.setUserName(username);
        ResourceLoadable loadable   = new ResourceLoadable() {
            @Override
            public Object decodeRes(BufferedInputStream inputStream) {
                return BitmapFactory.decodeStream(inputStream);
            }

            @Override
            public void onDownloadCompleted(Object result) {
                Bitmap img  = (Bitmap)result;
                if(img != null) {
                    holder.setUserPhoto(img);
                }
            }

            @Override
            public void onDownloadFailed(Exception exception) {
                PopUpException exc  = new PopUpException(exception.getMessage(),
                        PopUpException.MESSAGE_TOAST, R.drawable.ic_warning_yellow_24dp);
                taskView.displayMessage(exc);
            }
        };

        ResourceLoadTask loadImgTask   = new ResourceLoadTask(loadable);
        loadImgTask.execute(profileImg.getMediumImgUrl());
    }

    @Override
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

            View itemView = viewHolder.itemView;
            float height = (float) itemView.getBottom() - (float) itemView.getTop();
            float width = height / 3;

            if(dX < 0){
                p.setColor(Color.parseColor("#D32F2F"));
                RectF background = new RectF(
                        (float) itemView.getRight() + dX,
                        (float) itemView.getTop(),
                        (float) itemView.getRight(),
                        (float) itemView.getBottom());
                RectF icon_dest = new RectF(
                        (float) itemView.getRight()  - 2*width,
                        (float) itemView.getTop()    + width,
                        (float) itemView.getRight()  - width,
                        (float) itemView.getBottom() - width);
                canvas.drawRect(background,p);
                if(trashIcon != null){
                    canvas.drawBitmap(trashIcon, null, icon_dest, p);
                }
            }
        }
    }

    @Override
    public void onSwiped(View refView, RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.LEFT){
            tempUser   = userList.remove(position);
            taskView.removeUser(position, userList.size());
            tempPosition    = position;
            String msg  = String.format(Locale.ENGLISH, "%s is removed",
                    tempUser.getUsername());

            snackbar = Snackbar.make(refView, msg, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("UNDO", this);
            snackbar.show();
        }
    }

    @Override
    public void onResume() {
        if(isConnectedToInternet(taskView.getContext())){
            taskModel.prepareUserList();
        } else {
            PopUpException exc  = new PopUpException("Connect to the Internet to continue.",
                                    PopUpException.MESSAGE_2_BTN, R.drawable.ic_warning_yellow_24dp);
            exc.setListener(ButtonWrapper.POSITIVE_BUTTON, "Connect to WiFi", onConnectListener);
            exc.setListener(ButtonWrapper.NEGATIVE_BUTTON, "Quit", onQuitListener);
            taskView.displayMessage(exc);
        }
    }

    private boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return  (wifiInfo != null && wifiInfo.isConnected())     ||
                (mobileInfo != null && mobileInfo.isConnected());
    }

    @Override
    public void onPause() {
        if(snackbar != null)
            snackbar.dismiss();
    }

    @Override
    public void onRefresh() {
        taskModel.prepareUserList();
    }

    @Override
    public void onClick(View v) {
        userList.add(tempPosition, tempUser);
        taskView.insertUser(tempPosition);
    }

    @Override
    public void handleException(Exception exc) {
        if(exc instanceof PopUpException){
            taskView.displayMessage((PopUpException) exc);
        } else {
            PopUpException myExc    = new PopUpException(exc.getMessage(),
                    PopUpException.MESSAGE_TOAST, R.drawable.ic_warning_yellow_24dp);
            taskView.displayMessage(myExc);
        }
    }

    @Override
    public void updateUserList(ArrayList<User> userList) {
        this.userList   = userList;
        this.taskView.notifyUserListUpdated();
        this.taskView.stopRefreshingAnimation();
    }

    private DialogInterface.OnClickListener onConnectListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            taskView.startNewActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }
    };

    private DialogInterface.OnClickListener onQuitListener  = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            taskView.finishActivity();
        }
    };
}
