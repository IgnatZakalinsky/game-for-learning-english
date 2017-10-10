package Client;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RegWindow extends Frame implements WindowListener, Runnable {
    Thread t;
    volatile boolean q = true;
    RegWindow() {
        addWindowListener(this);
        setSize(300, 300);
        setTitle("Game: authorization");
        setVisible(true);
        t = new Thread(this, "rw"); // создание потока
        t.start(); // запуск потока (run())
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        q = false;
        setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void run() {
        while (q)
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
