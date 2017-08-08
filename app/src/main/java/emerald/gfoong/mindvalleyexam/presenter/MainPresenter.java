package emerald.gfoong.mindvalleyexam.presenter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import emerald.gfoong.mindvalleyexam.custom_view.UserViewHolder;
import emerald.gfoong.mindvalleyexam.intf.MvpMain;

/**
 * Created by FOONG on 8/8/2017.
 */

public class MainPresenter implements MvpMain.MvpPresenterM, MvpMain.MvpPresenterV {

    private MvpMain.MvpView taskView;
    private MvpMain.MvpModel taskModel;

    public MainPresenter(MvpMain.MvpView taskView){
        this.taskView   = taskView;
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
        return 0;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

    }

    @Override
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

    }

    @Override
    public void onSwiped(View refView, RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
}
