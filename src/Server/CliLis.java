package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CliLis implements Runnable {
    static int i = 0;
    BufferedReader in;
    String sin = "";
    boolean q = true;
    Thread t;
    Socket s;

    public CliLis(Socket s) {
        this.s = s;
        t = new Thread(this, "socket - " + String.valueOf(++i)); // создание потока
        t.start(); // запуск потока (run())
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            sin = in.readLine();
            if (sin.equals("regs")) {
                regs();
            }

            while (q) { // listening
                sin = in.readLine();
                synchronized (Serv.accs) {
                    System.out.println("text:" + sin);

                    if ((sin.equals("q")) || (sin == null)) {
                        q = false;
                        sin = Serv.accs.acc.get(s);
                        Serv.accs.acc.remove(sin);
                        Serv.accs.accOn.remove(sin);
                    }
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void regs() throws IOException {
        sin = in.readLine();
        synchronized (Serv.accs) {
            if (Serv.accs.accs.containsKey(sin)) {
                Serv.accs.accOn.put(sin, Serv.accs.accs.get(sin));
                Serv.accs.acc.put(s, sin);
            } else {
                System.out.println("non reg: [" + sin + "]");
                q = false;
            }
        }
    }
}
