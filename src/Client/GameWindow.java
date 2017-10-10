package Client;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameWindow extends Frame implements WindowListener, Runnable {
    Thread t;

    GameWindow() {
        addWindowListener(this);
        setSize(800, 600);
        setTitle("Game");
        setBackground(Color.BLACK);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 400,
                Toolkit.getDefaultToolkit().getScreenSize().height/2 - 300); // по центру
        setResizable(false); // не изменять размеры

        setVisible(true);
        t = new Thread(this, "gw"); // создание потока
        t.start(); // запуск потока (run())
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Cli.q = false;
        setVisible(false);
        System.exit(0);
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

    }
}
