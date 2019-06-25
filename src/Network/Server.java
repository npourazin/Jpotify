package Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    ExecutorService executorService;
    private boolean isRun;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(8080);
        this.executorService = Executors.newCachedThreadPool();
        isRun = true;
    }

    @Override
    public void run() {
        while (isRun) {
            try {
                Socket client = this.serverSocket.accept();
                this.executorService.submit(new Handler(client));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String sendData() {
        //TODO: send song name
        return "Example: Dean Lewis";
    }

    private static class Handler implements Runnable {

        private Socket client;

        public Handler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                int result;
                BufferedReader bfRead = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String string = bfRead.readLine();
                System.out.println("Message from client " + client.getInetAddress() + ":" + string);


                PrintWriter bfWriter = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                bfWriter.println(sendData());
                bfWriter.flush();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
