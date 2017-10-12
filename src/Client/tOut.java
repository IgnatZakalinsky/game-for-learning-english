package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class tOut extends Thread { // класс отображающий историю
    Thread t;
    Socket s;

    public tOut(Socket s) {
        this.s = s;
        t = new Thread(this, "tOut");
        t.start();
    }

    public void run() {
        String ti;
        BufferedReader in = null;
        ArrayList<ArrayList<String>> aas;
        int i;
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));


        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if (in != null) {
                    ti = in.readLine();
                    //System.out.println(ti);
                    aas = new ArrayList<>();
                    i = 0;
                    try {
                        i = Integer.parseInt(ti);
                    } catch (NumberFormatException ne) {
                        System.out.println("--NFE--" + ne);
                    }

                    for (int x = 0; x < i; x++) {
                        aas.add(new ArrayList<String>());
                        for (int y = 0; y < 5; y++) {
                            aas.get(x).add(in.readLine());
                            //System.out.println(x + ":" + y);
                        }
                    }

                    Cli.aa = aas;
                    for (ArrayList<String> aaa : aas) {
                        if (aaa.get(1).equals(Cli.setts[2])) {
                            try {
                                GameWindow.x = Integer.parseInt(aaa.get(2));
                                GameWindow.y = Integer.parseInt(aaa.get(3));
                            } catch (NumberFormatException nne) {
                                nne.printStackTrace();
                            }
                        }
                    }
                }
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
