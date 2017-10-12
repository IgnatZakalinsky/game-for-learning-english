package Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class UpCli implements Runnable {
    Thread t;

    UpCli() {
        t = new Thread(this, "socketUpper"); // создание потока
        t.start(); // запуск потока (run())
    }

    @Override
    public void run() {


        BufferedWriter out;
        boolean i;

        while (true) {
            synchronized (Serv.accs) {
                i = Serv.accs.change;
            }


            if (i) {

                synchronized (Serv.accs) {

                    for (Socket s : Serv.accs.acc.values()) { // перебор подключений

                        try {
                            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                            out.write(Serv.accs.acc.values().size() + "\n");
                            System.out.println("size:" + Serv.accs.acc.values().size());
                            out.flush();
                            for (ArrayList<String> s2 : Serv.accs.accOn.values()) { // отправка всей истории
                                for (String s3 : s2) {
                                    out.write(s3 + "\n");
                                    System.out.println("flush:" + s3);
                                    out.flush();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Serv.accs.change = false;
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
