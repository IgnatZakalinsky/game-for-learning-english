package Server;

public class UpCli implements Runnable{
    Thread t;

    UpCli() {
        t = new Thread(this, "socketUpper"); // создание потока
        t.start(); // запуск потока (run())
    }

    @Override
    public void run() {

        /*

    public static class ClassServ extends Thread { // класс для парралельного оповещение клиентов о изменении истории
        public ClassServ() {
            start();
        }

        public void run() {
            BufferedWriter out;
            boolean i;

            while (true) {
                synchronized (lock) {
                    i = change;
                }


                if (i) {

                    synchronized (lock) {

                        for (Socket s : as) { // перебор подключений

                            try {
                                out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                                out.write(at.size() + "\n");
                                out.flush();
                                for (String s2 : at) { // отправка всей истории
                                    out.write(s2 + "\n");
                                    out.flush();
                                }
                            } catch (IOException e) {
                                //e.printStackTrace();
                            }

                            //s.close();
                        }
                        change = false;
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

    }*/

    }
}
