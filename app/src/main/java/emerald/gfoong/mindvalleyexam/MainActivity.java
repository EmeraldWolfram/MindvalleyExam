package emerald.gfoong.mindvalleyexam;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.BindView;
import butterknife.ButterKnife;
import emerald.gfoong.mindvalleyexam.custom_view.UserViewHolder;
import emerald.gfoong.mindvalleyexam.intf.MvpMain;
import emerald.gfoong.mindvalleyexam.model.MainModel;
import emerald.gfoong.mindvalleyexam.presenter.MainPresenter;
import emerald.gfoong.mindvalleyexam.tools.PopUpException;
import emerald.gfoong.mindvalleyexam.tools.PopUpManager;

public class MainActivity extends AppCompatActivity implements MvpMain.MvpView{

    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.list_view_container)
    SwipeRefreshLayout listViewContainer;

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
        MainPresenter   presenter   = new MainPresenter(this);
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
    }


    @Override
    protected void onResume() {
        super.onResume();
        taskPresenter.onResume();
    }

    @Override
    public void onRefresh() {
        // Your code to refresh the list here.

        listViewContainer.setRefreshing(false);
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
    public void removeRecord(int position, int newSize) {
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, newSize);
    }

    @Override
    public void insertRecord(int position) {
        adapter.notifyItemInserted(position);
    }

}

/*
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

 */
