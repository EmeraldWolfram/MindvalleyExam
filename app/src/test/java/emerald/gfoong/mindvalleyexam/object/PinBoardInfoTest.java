package emerald.gfoong.mindvalleyexam.object;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by FOONG on 8/8/2017.
 */
public class PinBoardInfoTest {
    @Test
    public void setColor_TestParsingStringToHex() throws Exception {
        PinBoardInfo pinBoardInfo   = new PinBoardInfo();
        String color    = "#101028";

        pinBoardInfo.setColor(color);

        assertEquals(1052712, pinBoardInfo.getColor().intValue());
    }

}