package Client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameWindow extends Frame implements WindowListener, Runnable {
    Key k;
    static int x = -300, y = -300;
    boolean q = true;
    Thread t;
    volatile private static BufferedImage b2 = new BufferedImage(800, 600, 1);

    GameWindow(Key k) {
        this.k = k;
        addKeyListener(k);
        k.reg(this);
        addWindowListener(this);
        setSize(800, 600);
        setTitle("Game");
        setBackground(Color.BLACK);

        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 400,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 300); // по центру
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
        go();
    }

    private void go() {
        while (q) {
            //temp const
            BufferedImage bi = null, gg = null;
            try {
                bi = ImageIO.read(new File("src\\Client\\local1.jpg"));
                gg = ImageIO.read(new File("src\\Client\\gg.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Graphics2D g2d;
            g2d = (Graphics2D) b2.getGraphics();

            //g2d.rotate(Math.PI / 50 * s.a, s.x + 75, s.y + 75);
            g2d.drawImage(bi, x, y, 1300, 1300, null);
            g2d.drawImage(gg, 370, 270, 60, 60, null);

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }

    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(b2, 0, 0, this);
    }
}
