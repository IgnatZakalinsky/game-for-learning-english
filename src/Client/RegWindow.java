package Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RegWindow extends Frame implements WindowListener, Runnable, ActionListener {
    TextField tf;
    Button b;
    Thread t;
    volatile boolean q = true;
    RegWindow() {
        addWindowListener(this);
        setSize(300, 300);
        setTitle("Game: authorization");
        setBackground(Color.BLACK);
        setLayout(null); // не выравнивать по сетке элементы окна
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 150,
                Toolkit.getDefaultToolkit().getScreenSize().height/2 - 150); // по центру
        setResizable(false); // не изменять размеры

        tf = new TextField(Cli.setts[1]);
        tf.setEchoChar('*'); // для пороля
        tf.setBounds(40, 150, 220, 40);
        tf.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 40));
        add(tf);
        b = new Button("connect...");
        b.setBounds(40, 210, 220, 40);
        b.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, 40));
        b.addActionListener(this);
        add(b);

        setVisible(true);
        t = new Thread(this, "rw"); // создание потока
        t.start(); // запуск потока (run())
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Cli.q = false;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Cli.setts[1] = tf.getText();
        q = false;
        setVisible(false);
    }
}
