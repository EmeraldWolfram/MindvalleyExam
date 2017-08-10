package emerald.gfoong.mindvalleyexam.intf;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import emerald.gfoong.mindvalleyexam.custom_view.UserViewHolder;
import emerald.gfoong.mindvalleyexam.custom_object.User;

/**
 * Created by FOONG on 8/8/2017.
 */

public interface MvpMain {

    interface MvpView extends ActivityView, SwipeRefreshLayout.OnRefreshListener{
        void removeUser(int position, int newSize);
        void insertUser(int position);
        void notifyUserListUpdated();
        void stopRefreshingAnimation();
        Context getContext();
    }

    interface MvpPresenterV extends View.OnClickListener{
        void onResume();
        void onPause();

        void onRefresh();

        void onBindViewHolder(UserViewHolder holder, int position);
        int getItemCount();
        boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                       RecyclerView.ViewHolder target);
        void onSwiped(View refView, RecyclerView.ViewHolder viewHolder, int direction);
        void onChildDraw(Canvas canvas, RecyclerView recyclerView,
                         RecyclerView.ViewHolder viewHolder, float dX, float dY,
                         int actionState, boolean isCurrentlyActive);

    }

    interface MvpPresenterM {
        void handleException(Exception exc);
        void updateUserList(ArrayList<User> userList);
    }

    interface MvpModel extends ResourceLoadable {
        void prepareUserList();
    }

}
