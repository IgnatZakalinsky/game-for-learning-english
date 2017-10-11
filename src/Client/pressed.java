package Client;

import java.awt.event.KeyEvent;

public class pressed implements Runnable {
    static int i = 0;
    final int STEP = 5;
    Thread t;
    GameWindow gw;
    volatile boolean ex = false, ey = false, e_y = false, e_x = false;
    boolean q = true;

    pressed(GameWindow gw) {
        this.gw = gw;
        t = new Thread(this, "pressed" + ++i);
        t.start();
    }

    @Override
    public void run() {
        while (q) {
            if (ey) GameWindow.y += STEP;
            if (e_y) GameWindow.y -= STEP;
            if (ex) GameWindow.x += STEP;
            if (e_x) GameWindow.x -= STEP;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        //q = true;
    }

    void setE(char e, boolean p) {
        if (p) {
            if (e == 'w') ey = true;
            else if (e == 's') e_y = true;
            else if (e == 'a') ex = true;
            else if (e == 'd') e_x = true;
        } else {
            if (e == 'w') ey = false;
            else if (e == 's') e_y = false;
            else if (e == 'a') ex = false;
            else if (e == 'd') e_x = false;
        }
    }
}
