package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class tOut extends Thread { // класс отображающий историю
    Thread t;
    Socket s;

    public tOut(Socket s) {
        this.s = s;
        t = new Thread(this, "tOut");
        t.start();
    }

    public void run() {
        BufferedReader in = null;
        //int i;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //i = Integer.parseInt(in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (in != null) System.out.println(in.readLine());
            } catch (IOException e) {
                System.out.println("c in tOut");
                Cli.connect();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                s = Cli.s;
                try {
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
