package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Cli {
    static volatile Socket s;
    static volatile BufferedWriter out;
    static volatile BufferedReader in;
    static volatile String te;
    static volatile boolean q = true;
    static volatile byte[] b = new byte[4];

    public static void main(String[] args) {
        //load settings
        int i[] = {178, 124, 163, 77}; // real IP
        b[0] = (byte) i[0];
        b[1] = (byte) i[1];
        b[2] = (byte) i[2];
        b[3] = (byte) i[3];
        // {-78, 124, -93, 77} server IP

        //init connect

        //connect
        System.out.println("подключаюсь...");
        try {
            s = new Socket(InetAddress.getByAddress(b), 5050); //connect
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            System.out.println("!Сервер отключён!");
            System.out.println("повторная попытка подключения через 10с...");
            boolean qq = true;
            while (qq) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e.printStackTrace();
                }

                System.out.println("подключаюсь...");
                try {
                    s = new Socket(InetAddress.getByAddress(b), 5050); //connect
                    out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    qq = false;
                } catch (IOException e2) {
                    System.out.println("!Сервер отключён!");
                    System.out.println("повторная попытка подключения через 10с...");
                }
            }
        }
        System.out.println("Готово!");

        //start window
        while (q) {
            tIn(); // для пополнения истории
        }
    }

    private static String s() { //временная функция ввода из консоли
        String ss = null;
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            ss = is.readLine();
            if (ss.length() == 0) return " ";
            if (ss.equals("q")) q = false;
        } catch (IOException e) { System.out.println("Error in: " + e); }
        return ss;
    }

    public static void tIn() { //для пополнения истории
        try {
            try {
                out.write(s() + "\n");
                out.flush();
                System.out.println("[flush]");
                te = in.readLine();
                System.out.println(te);
                if (te.equals("q")) q = false;
            } catch (Exception e3) {
                System.out.println("!Сервер отключён!");
                System.out.println("подключаюсь...");
                try {
                    s = new Socket(InetAddress.getByAddress(b), 5050); //connect
                    out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                } catch (IOException e) {
                    System.out.println("!Сервер отключён!");
                    System.out.println("повторная попытка подключения через 10с...");
                    boolean qq = true;
                    while (qq) {
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e1) {
                            e.printStackTrace();
                        }
                        System.out.println("подключаюсь...");
                        try {
                            s = new Socket(InetAddress.getByAddress(b), 5050); //connect
                            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                            qq = false;
                        } catch (IOException e2) {
                            System.out.println("!Сервер отключён!");
                            System.out.println("повторная попытка подключения через 10с...");
                        }
                    }
                }
                System.out.println("Готово!");
            }
            if (!q) s.close();
            //System.out.println("go");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*        new tOut(); // класс отображающий историю
    }

    public static class tOut extends Thread { // класс отображающий историю
        public tOut() {
            start();
        }

        public void run() {
            BufferedReader in;
            int i;

            while (q) {

                try {


                    in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    i = Integer.parseInt(in.readLine());

                    for (int z = 0; z < 50; ++z) System.out.println();

                    for (int x = 0; x < i; x++) {
                        System.out.println(in.readLine());
                    }

                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }

        }
    }
    */
