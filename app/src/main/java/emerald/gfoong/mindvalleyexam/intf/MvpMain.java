package emerald.gfoong.mindvalleyexam.intf;

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
        void removeRecord(int position, int newSize);
        void insertRecord(int position);
    }

    interface MvpPresenterV {
        void onResume();
        void onPause();

        void onBindViewHolder(UserViewHolder holder, int position);
        int getItemCount();
        boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                       RecyclerView.ViewHolder target);
        void onSwiped(View refView, RecyclerView.ViewHolder viewHolder, int direction);
        void onChildDraw(Canvas canvas, RecyclerView recyclerView,
                         RecyclerView.ViewHolder viewHolder, float dX, float dY,
                         int actionState, boolean isCurrentlyActive);

    }

    interface MvpPresenterM {}

    interface MvpModel {
        void addItem(User user);
        void removeItem(User cr);
        ArrayList<User> retrieveRecords();

    }

}
