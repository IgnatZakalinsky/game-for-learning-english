package Server;

import java.net.Socket;

public class CliLis implements Runnable {
    static int i = 0;
    Thread t;
    Socket s;

    public CliLis(Socket s) {
        this.s = s;
        t = new Thread(this, "socket - " + String.valueOf(++i)); // создание потока
        t.start(); // запуск потока (run())
    }

    @Override
    public void run() {

        /*
            BufferedReader in;
            boolean q = true;
            String sin = "";



                try {
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    sin = in.readLine();

                    if(sin.equals("regs") { regs(); }

                    while (q) {
                    synchronized (lock) {
                        Serv.at.add(t); // добавление в историю

                        System.out.println(t);

                        Serv.change = true; // оповещение о изменении

                        if ((sin.equals("q")) || (sin == null)) q = false;
                    }
                    }
                } catch (java.io.IOException e) { e.printStackTrace(); }

                */
    }
}
