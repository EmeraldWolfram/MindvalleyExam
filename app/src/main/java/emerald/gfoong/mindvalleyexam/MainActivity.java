package emerald.gfoong.mindvalleyexam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.BindBitmap;
import butterknife.BindView;
import butterknife.ButterKnife;
import emerald.gfoong.mindvalleyexam.custom_view.UserViewHolder;
import emerald.gfoong.mindvalleyexam.intf.MvpMain;
import emerald.gfoong.mindvalleyexam.model.MainModel;
import emerald.gfoong.mindvalleyexam.presenter.MainPresenter;
import emerald.gfoong.mindvalleyexam.lib.PopUpException;
import emerald.gfoong.mindvalleyexam.lib.PopUpManager;

public class MainActivity extends AppCompatActivity implements MvpMain.MvpView{

    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.list_view_container)
    SwipeRefreshLayout listViewContainer;
    @BindBitmap(R.drawable.ic_delete_white_24dp)
    Bitmap trashIcon;

    private PopUpManager popUpManager;
    private UserListAdapter adapter;
    private MvpMain.MvpPresenterV taskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initMvp();
    }

    private void initMvp(){
        popUpManager                = new PopUpManager(this);
        MainPresenter   presenter   = new MainPresenter(this, trashIcon);
        MainModel       model       = new MainModel(presenter);
        presenter.setTaskModel(model);
        taskPresenter               = presenter;
    }

    private void initView(){
        // Setup refresh listener which triggers new data loading
        listViewContainer.setOnRefreshListener(this);
        // Configure the refreshing colors
        listViewContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        adapter = new UserListAdapter();
        ItemTouchHelper.SimpleCallback callback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
                    @Override
                    public boolean onMove(RecyclerView              recyclerView,
                                          RecyclerView.ViewHolder   viewHolder,
                                          RecyclerView.ViewHolder   target) {
                        return taskPresenter.onMove(recyclerView, viewHolder, target);
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        taskPresenter.onSwiped(listViewContainer, viewHolder, direction);
                    }

                    @Override
                    public void onChildDraw(Canvas canvas, RecyclerView rccView,
                                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                            int actionState, boolean isActive) {
                        taskPresenter.onChildDraw(canvas, rccView, viewHolder, dX, dY, actionState, isActive);
                        super.onChildDraw(canvas, rccView, viewHolder, dX, dY, actionState, isActive);
                    }
                };

        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(listView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        taskPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        taskPresenter.onPause();
    }

    @Override
    public void onRefresh() {
        taskPresenter.onRefresh();
    }

    @Override
    public void displayMessage(PopUpException exc) {
        popUpManager.displayException(exc);
    }

    private class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {

        @Override
        public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            View v = LayoutInflater.from(context).inflate(R.layout.view_pin_item, parent, false);
            return new UserViewHolder(v);
        }

        @Override
        public void onBindViewHolder(UserViewHolder holder, int position) {
            taskPresenter.onBindViewHolder(holder, position);
        }

        @Override
        public int getItemCount() {
            return taskPresenter.getItemCount();
        }
    }

    @Override
    public void removeUser(int position, int newSize) {
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, newSize);
    }

    @Override
    public void insertUser(int position) {
        adapter.notifyItemInserted(position);
    }

    @Override
    public void notifyUserListUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefreshingAnimation() {
        listViewContainer.setRefreshing(false);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startNewActivity(Intent intent) {
        this.startActivity(intent);
    }

    @Override
    public void finishActivity() {
        this.finish();
    }
}
