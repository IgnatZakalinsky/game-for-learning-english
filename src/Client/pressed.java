package Client;

import java.awt.event.KeyEvent;

public class pressed implements Runnable {
    static int i = 0;
    final int STEP = 5;
    Thread t;
    GameWindow gw;
    volatile char e = '0';
    boolean q = true;

    pressed(GameWindow gw) {
        this.gw = gw;
        t = new Thread(this, "pressed" + ++i);
        t.start();
    }

    @Override
    public void run() {
        while (q) {
            if (e == 'w') GameWindow.y += STEP;
            if (e == 's') GameWindow.y -= STEP;
            if (e == 'a') GameWindow.x += STEP;
            if (e == 'd') GameWindow.x -= STEP;

            try {
                Thread.sleep(50);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
        //q = true;
    }

    public void setE(char e, boolean p) {
        if (p) this.e = e;
        else if (this.e == e) this.e = '0';
    }
}
