package emerald.gfoong.mindvalleyexam.model;

import java.util.ArrayList;

import emerald.gfoong.mindvalleyexam.custom_object.User;
import emerald.gfoong.mindvalleyexam.intf.MvpMain;

/**
 * Created by FOONG on 8/8/2017.
 */

public class MainModel implements MvpMain.MvpModel {

    private MvpMain.MvpPresenterM taskPresenter;

    public MainModel(MvpMain.MvpPresenterM taskPresenter){
        this.taskPresenter  = taskPresenter;
    }

    @Override
    public ArrayList<User> retrieveRecords() {
        return null;
    }

    @Override
    public void addItem(User user) {

    }

    @Override
    public void removeItem(User cr) {

    }
}
