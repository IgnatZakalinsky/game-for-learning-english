package Client;

public class pressed implements Runnable {
    final int STEP = 5;
    Thread t;
    GameWindow gw;
    volatile boolean ex = false, ey = false, e_y = false, e_x = false;

    pressed(GameWindow gw) {
        this.gw = gw;
        t = new Thread(this, "pressed");
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            if (!Cli.tryConnect) {
                if (ey) {
                    GameWindow.y += STEP;
                    Cli.tIn("y");
                    Cli.tIn(String.valueOf(GameWindow.y));
                }
                if (e_y) {
                    GameWindow.y -= STEP;
                    Cli.tIn("y");
                    Cli.tIn(String.valueOf(GameWindow.y));
                }
                if (ex) {
                    GameWindow.x += STEP;
                    Cli.tIn("x");
                    Cli.tIn(String.valueOf(GameWindow.x));
                }
                if (e_x) {
                    GameWindow.x -= STEP;
                    Cli.tIn("x");
                    Cli.tIn(String.valueOf(GameWindow.x));
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
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
