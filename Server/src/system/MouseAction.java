package system;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseAction extends Robot {
    public MouseAction() throws AWTException {
    }
    public void click(int x, int y, int button){
        if(button >0) {
            this.mouseMove(x,y);
            System.out.println("Click");
            //        this.delay(100);
            if (button == 1) button = InputEvent.BUTTON1_DOWN_MASK;
            if (button == 2) button = InputEvent.BUTTON2_DOWN_MASK;
            if (button == 3) button = InputEvent.BUTTON3_DOWN_MASK;
            this.mousePress(button);
            this.mouseRelease(button);
        }
    }
}
