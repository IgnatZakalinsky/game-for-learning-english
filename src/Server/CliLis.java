package Server;

import java.io.*;
import java.net.Socket;

public class CliLis implements Runnable {
    static int i = 0;
    volatile BufferedReader in;
    //volatile BufferedWriter out;
    volatile String sin = "", sinIn = "55";
    volatile boolean q = true;
    volatile Thread t;
    volatile Socket s;

    public CliLis(Socket s) {
        this.s = s;
        t = new Thread(this, "socket - " + String.valueOf(++i)); // создание потока
        t.start(); // запуск потока (run())
    }

    @Override
    public void run() {
        System.out.println(t.getName());
        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            sin = in.readLine();
            if ((sin != null) && (sin.equals("regs"))) {

                /*out.write("+++++++++++++++++");
                out.flush();
                out.write("111");
                out.flush();*/
                System.out.print("+");

                regs();
                boolean ent = true;
                String com = "";
                while (q) { // listening
                    sin = in.readLine();
                    synchronized (Serv.accs) {
                        //System.out.println("text:" + sin);
                        if ((sin == null) || (sin.equals("q"))) {
                            System.out.println("disconnect");
                            Serv.accs.acc.remove(sinIn);
                            Serv.accs.accOn.remove(sinIn);
                            s.close();
                            q = false;
                        } else if (ent) {
                            com = sin;
                            ent = false;
                        } else {
                            ent = true;
                            Serv.accs.change = true;
                            if (com.equals("x")) Serv.accs.accOn.get(sinIn).set(2, sin);
                            else if (com.equals("y")) Serv.accs.accOn.get(sinIn).set(3, sin);

                            for (String sx : Serv.accs.accOn.get(sinIn))
                                System.out.print(":" + sx + ":");
                            System.out.println();
                        }


                    }
                }
            } else {
                //out.write("q");
                //out.flush();
                System.out.println("no client = " + sin);
                s.close();
            }
        } catch (java.io.IOException e) {
            if (e.toString().equals("java.net.SocketException: Connection reset")) {
                System.out.println("Connection reset");
            } else {
                e.printStackTrace();
                System.out.println("error from listen");
            }
        }
    }

    private void regs() throws IOException {
        sin = in.readLine();
        synchronized (Serv.accs) {
            if (Serv.accs.accs.containsKey(sin)) {
                //out.write("++");
                //out.flush();
                Serv.accs.accOn.put(sin, Serv.accs.accs.get(sin));
                Serv.accs.acc.put(sin, s);
                sinIn = sin;
                System.out.println("[add]");
            } else {
                System.out.println("non reg: [" + sin + "]");
                s.close();
                //out.write("q");
                //out.flush();
                q = false;
            }
        }
    }
}
