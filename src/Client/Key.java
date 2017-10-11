package Client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
    GameWindow gw;
    pressed p = new pressed(gw);

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        p.setE(e.getKeyChar(), true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        p.setE(e.getKeyChar(), false);
    }

    public void reg(GameWindow gameWindow) {
        gw = gameWindow;
    }
}
