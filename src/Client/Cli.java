package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;

public class Cli {
    static volatile Socket s;
    static volatile BufferedWriter out;
    //static volatile BufferedReader in;
    static volatile String[] setts = new String[10];
    static volatile RegWindow rw;
    static volatile boolean q = true;
    static byte[] bip = new byte[4];

    public static void main(String[] args) {
        //load settings
        load();
        //init connect
        rw = new RegWindow();
        try {
            System.out.println("ждём...");
            rw.t.join(); // ждёт пока завершиться t поток
        } catch (InterruptedException e) {
            System.out.println("ошибка ожидания: " + e);
        }
        rw = null;


        //connect
        if (q) {
            System.out.println("подключаюсь...");
            try {
                s = new Socket(InetAddress.getByAddress(bip), 5050); //connect
                out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                //in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out.write(setts[0] + "\n");
                out.flush();
                out.write(setts[1] + "\n");
                out.flush();
            } catch (IOException e) {
                connect();
            }
            System.out.println("Готово!");
        }

        //start window
        while (q) {
            tIn(); // для пополнения истории
        }
        System.exit(0);
    }

    private static void load() {
        LinkedList<Byte> b = new LinkedList<>();
        FileInputStream fin;
        try {
            fin = new FileInputStream("src\\Client\\saveSettings.txt");
            do {
                b.add((byte) fin.read()); // побайтовое чтение
            } while (b.getLast() != -1);
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        b.removeLast();
        byte[] b2 = new byte[b.size()];
        int i = 0, j;
        for (byte b3 : b) {
            b2[i++] = b3;
        }
        String st = new String(b2);
        String[] ss = st.split("\r\n");
        for (String s0 : ss) System.out.println("[" + s0 + "]");
        int ip[] = new int[4]; // real IP 178.124.163.77
        try {
            ip[0] = Integer.parseInt(ss[0]);
            ip[1] = Integer.parseInt(ss[1]);
            ip[2] = Integer.parseInt(ss[2]);
            ip[3] = Integer.parseInt(ss[3]);
            bip[0] = (byte) ip[0];
            bip[1] = (byte) ip[1];
            bip[2] = (byte) ip[2];
            bip[3] = (byte) ip[3]; // {-78, 124, -93, 77} server IP
            j = Integer.parseInt(ss[4]);
            System.arraycopy(ss, 5, setts, 0, j);
        } catch (Exception e) {
            System.out.println(e + "\nfile saveSettings.txt is corrupted");
        }
        for (String ii : setts) System.out.println("{" + ii + "}");
    }

    private static void connect() {
        System.out.println("!Сервер отключён!");
        System.out.println("повторная попытка подключения через 10с...");
        boolean qq = true;
        while (qq) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("подключаюсь...");
            try {
                rw = new RegWindow();
                try {
                    System.out.println("ждём...");
                    rw.t.join(); // ждёт пока завершиться t поток
                } catch (InterruptedException e) {
                    System.out.println("ошибка ожидания: " + e);
                }
                rw = null;

                if (q) {
                    s = new Socket(InetAddress.getByAddress(bip), 5050); //connect
                    out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    //in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    out.write(setts[0] + "\n");
                    out.flush();
                    out.write(setts[1] + "\n");
                    out.flush();
                }
                qq = false;
            } catch (IOException e2) {
                System.out.println("!Сервер отключён!");
                System.out.println("повторная попытка подключения через 10с...");
            }
        }
    }

    private static String s() { //временная функция ввода из консоли
        String ss = null;
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
            ss = is.readLine();
            if (ss.length() == 0) return " ";
            if (ss.equals("q")) q = false;
        } catch (IOException e) {
            System.out.println("Error in: " + e);
        }
        return ss;
    }

    public static void tIn() { //для пополнения истории
        try {
            try {
                out.write(s() + "\n");
                out.flush();
                System.out.println("[flush]");
            } catch (Exception e3) {
                connect();
                System.out.println("Готово!");
            }
            if (!q) s.close();
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
