package Server;

import java.net.ServerSocket;
import java.net.Socket;

public final class Serv {
    static final Accs accs = new Accs();

    public static void main(String[] args) {
        //Thread for update clients
        new UpCli(); // класс для парралельного оповещение клиентов о изменении истории

        Socket s;
        try {
            ServerSocket ss = new ServerSocket(5050); // open port
            while (true) {
                s = ss.accept();
                //Thread for listen socket
                new CliLis(s); // класс для парралельной обработки
            }
        } catch (Exception e) {
            System.out.println("error in server while");
            e.printStackTrace();
        }
    }
}
