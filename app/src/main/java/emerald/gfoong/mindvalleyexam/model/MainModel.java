package emerald.gfoong.mindvalleyexam.model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import emerald.gfoong.mindvalleyexam.custom_object.PinBoardInfo;
import emerald.gfoong.mindvalleyexam.custom_object.User;
import emerald.gfoong.mindvalleyexam.intf.MvpMain;
import emerald.gfoong.mindvalleyexam.lib.JsonProcessor;
import emerald.gfoong.mindvalleyexam.lib.ResourceLoadTask;

/**
 * Created by FOONG on 8/8/2017.
 */

public class MainModel implements MvpMain.MvpModel {

    private MvpMain.MvpPresenterM taskPresenter;

    public MainModel(MvpMain.MvpPresenterM taskPresenter){
        this.taskPresenter  = taskPresenter;
    }

    @Override
    public void prepareUserList() {
        ResourceLoadTask boardDownloadTask   = new ResourceLoadTask(this);
        boardDownloadTask.execute("http://pastebin.com/raw/wgkJgazE");
    }


    @Override
    public void onDownloadCompleted(Object result) {
        ArrayList<User> userList = new ArrayList<>();
        try{
            List<PinBoardInfo> infoList = JsonProcessor.parsePinBoardInformation((String)result);
            for(int i = 0; i < infoList.size(); i++){
                userList.add(infoList.get(i).getUser());
            }
        } catch (Exception exc) {
            taskPresenter.handleException(exc);
        }

        taskPresenter.updateUserList(userList);
    }

    @Override
    public void onDownloadFailed(Exception exception) {
        taskPresenter.handleException(exception);
    }

    @Override
    public Object decodeRes(BufferedInputStream inputStream) throws Exception{
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }
}