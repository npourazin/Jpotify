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
    private static int numberOfClients=0;
    private static int lastClientId=0;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(8080);
        this.executorService = Executors.newCachedThreadPool();
        isRun = true;
    }

    @Override
    public void run() {
        System.out.println("Welcome");
        System.out.println("ready to connect!!");
        while (isRun) {
            try {
                Socket socket = this.serverSocket.accept();
                System.out.println("new client ip: "+socket.getInetAddress());
                Handler handler = new Handler(socket);
                handler.setClientId(++lastClientId);
                numberOfClients++;
                System.out.println("client ID: "+lastClientId);

                this.executorService.submit(handler);
                System.out.println("ready to accept other clients...");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String processRequest(String request ){
        return "";
    }

    public static Package sendData(String request) {
        Package pac = new Package(request);
        //TODO what is the package that is sent??
        return pac;
    }

    private static class Handler implements Runnable {

        private Socket client;
        private int clientId;

        public Handler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                int result;
                BufferedReader inp = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

//                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                while (true){
                    String request = "";
                    if(request.equals("quit") || request==null){
                        System.out.println("GoodBye Client No."+clientId);
                        break;
                    }
                    request=inp.readLine();
                    String requestedAbsolutePath = processRequest(request);//?
                    oos.writeObject(sendData(requestedAbsolutePath));//hello
                }

//                try {
//
//                }catch (IOException e){
//
//                }



                client.close();
                Server.numberOfClients--;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public int getClientId() {
            return clientId;
        }

        public void setClientId(int clientId) {
            this.clientId = clientId;
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
