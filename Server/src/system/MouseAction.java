package system;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseAction extends Robot implements Runnable{
    int x;
    int y;
    int button;

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setButton(int button) {this.button = button;}

    public int getX() {return x;}
    public int getY() {return y;}
    public int getButton() {return button;}

    public MouseAction() throws AWTException {}

    public void click(int x, int y, int button){
        if(button >0) {
            this.mouseMove(x,y);
            //        this.delay(100);
            if (button == 1) button = InputEvent.BUTTON1_DOWN_MASK;
            if (button == 2) button = InputEvent.BUTTON2_DOWN_MASK;
            if (button == 3) button = InputEvent.BUTTON3_DOWN_MASK;
            this.mousePress(button);
            this.mouseRelease(button);
        }
    }

    @Override
    public void run() {
        System.out.print("");
        this.click(this.getX(),this.getY(),this.getButton());
    }
}
