package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class Cli {
    static volatile ArrayList<ArrayList<String>> aa = new ArrayList<>();
    static volatile Socket s;
    static volatile boolean tryConnect = false;
    static volatile BufferedWriter out;
    //static volatile BufferedReader in;
    static volatile String[] setts = new String[10];
    static volatile RegWindow rw;
    static volatile GameWindow gw;
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
        System.out.println(":" + setts[0] + ":" + setts[1] + ":" + setts[2]);


        //connect
        if (q) {
            System.out.println("подключаюсь...");
            try {
                tryConnect = true;
                s = new Socket(InetAddress.getByAddress(bip), 5050); //connect
                out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                //in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                out.write(setts[0] + "\n");
                out.flush();
                out.write(setts[1] + "\n");
                out.flush();
                out.write(setts[2] + "\n");
                out.flush();
                tryConnect = false;
                System.out.println("Готово!");
            } catch (IOException e) {
                tryConnect = false;
                connect();
            }
        }

        //start window
        if (q) {
            Key k = new Key();
            gw = new GameWindow(k);
            new tOut(s);
        }

        while (q) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    static void connect() {
        if (!tryConnect) {
            tryConnect = true;
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
                        out.write(setts[2] + "\n");
                        out.flush();
                    }
                    qq = false;
                } catch (IOException e2) {
                    System.out.println("!Сервер отключён!");
                    System.out.println("повторная попытка подключения через 10с...");
                }
            }
            System.out.println("Готово!");
            tryConnect = false;
        }
    }

    public static void tIn(String sx) { //для пополнения истории
        try {
            try {
                out.write(sx + "\n");
                out.flush();
            } catch (Exception e3) {
                System.out.println("c in tIn");
                connect();
            }
            if (!q) s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

