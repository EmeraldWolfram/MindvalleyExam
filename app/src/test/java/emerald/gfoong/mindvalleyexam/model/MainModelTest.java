package emerald.gfoong.mindvalleyexam.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import emerald.gfoong.mindvalleyexam.custom_object.PinBoardInfo;
import emerald.gfoong.mindvalleyexam.lib.JsonProcessor;

import static org.junit.Assert.*;

/**
 * Created by FOONG on 9/8/2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class MainModelTest {
    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void decodeRes_testDownloadJsonLogic() throws Exception {

        BufferedInputStream inputStream = new BufferedInputStream(new URL("http://pastebin.com/raw/wgkJgazE").openStream());

        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        List<PinBoardInfo> list = JsonProcessor.parsePinBoardInformation(total.toString());
        assertEquals(10, list.size());
    }

    @Test
    public void decodeRes_TestRealCase() throws Exception {

    }

}