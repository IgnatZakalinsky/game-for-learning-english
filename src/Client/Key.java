package Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
    GameWindow gw;
    final int STEP = 5;
    volatile boolean q = true;
    pressed p = new pressed(gw);

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("k");
        p.setE(e.getKeyChar(), true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("r");
        p.setE(e.getKeyChar(), false);
    }

    public void reg(GameWindow gameWindow) {
        gw = gameWindow;
    }
}
