package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

final class Accs {
    TreeMap<String, ArrayList<String>> accs = new TreeMap<>();
    TreeMap<String, Socket> acc = new TreeMap<>();
    TreeMap<String, ArrayList<String>> accOn = new TreeMap<>();
    boolean change = false;

    Accs() {
        //read file
        LinkedList<Byte> b = new LinkedList<>();
        FileInputStream fin;
        try {
            fin = new FileInputStream("src\\Server\\accs.txt");
            do {
                b.add((byte) fin.read()); // побайтовое чтение
            } while (b.getLast() != -1);
            fin.close();
        } catch (IOException e) { e.printStackTrace(); }
        b.removeLast();
        byte[] b2 = new byte[b.size()];
        int i = 0, j = 1;
        for (byte b3 : b) {
            b2[i++] = b3;
        }
        String st = new String(b2);
        String[] ss = st.split("\r\n");
        for (String s0 : ss) System.out.println("[" + s0 + "]");

        try {
            i = Integer.parseInt(ss[0]);
            for (int ac = 0; ac < i; ac++) {
                ArrayList<String> ap = new ArrayList<>();
                for (int p = 0; p < 5; p++) {
                    ap.add(ss[j++]);
                    System.out.print(":" + ss[j - 1]);
                }
                accs.put(ap.get(0), ap);
                System.out.println(":acc:" + ac);
            }
        } catch (Exception e) { System.out.println(e + "\nfile accs.txt is corrupted"); }


    }

}
