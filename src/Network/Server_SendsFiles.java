package Network;

import Logic.PlayerManager;
import GUI.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server_SendsFiles implements Runnable{
    private ServerSocket serverSocket;
    ExecutorService executorService;
    private boolean isRun;
    private static int numberOfClients = 0;
    private static int lastClientId = 0;

    public Server_SendsFiles() throws IOException {
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
                System.out.println("new client ip: " + socket.getInetAddress());
                Handler handler = new Handler(socket);
                handler.setClientId(++lastClientId);
                numberOfClients++;
                System.out.println("client ID: " + lastClientId);

                this.executorService.submit(handler);
                System.out.println("ready to accept other clients...");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String processRequest() {
        if (PlayerManager.getsP() != null) {
            return PlayerManager.getsP().getFileName();
        } else {
            try {
                Scanner sc = new Scanner(new FileReader("src/LastSongListened.txt"));
                return sc.nextLine();
            } catch (FileNotFoundException e) {
                return null;
            }
        }
    }

    public static Package makePackageForData(String request) {
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
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;

            if( friendsActivityArea.isAskedForLastListened() ) {
                String lastListenedSongPath="";
                try {
                    Scanner sc= new Scanner(new FileReader("src/LastSongListened.txt"));
                    if(sc.hasNext()){
                        lastListenedSongPath=sc.nextLine();
                    }else{
                        client.close();
                        numberOfClients--;
                        return;
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (lastListenedSongPath.equals("")) {
                        client.close();
                        numberOfClients--;
                        return;
                    }

                    File myFile = new File(lastListenedSongPath);
                    byte[] mybytearray = new byte[(int) myFile.length()];

                    fis = new FileInputStream(myFile);
                    bis = new BufferedInputStream(fis);
                    bis.read(mybytearray, 0, mybytearray.length);

                    os = client.getOutputStream();
                    System.out.println("Sending " + lastListenedSongPath + "(" + mybytearray.length + " bytes)");

                    os.write(mybytearray, 0, mybytearray.length);
                    os.flush();

                    System.out.println("Done.");

                    client.close();
                    numberOfClients--;
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (bis != null) bis.close();
                        if (os != null) os.close();
                        if (client != null) client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                //asked for the shared playlist


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
            Server_SendsFiles server_sendsFiles = new Server_SendsFiles();
            server_sendsFiles.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
