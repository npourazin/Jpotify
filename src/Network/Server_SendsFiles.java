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
            //now
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
        private boolean lastListenedIsWantedFlag=true;


        public Handler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;

            try {
                BufferedReader inp = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
                //every command can start with client's name
                //ask the client what their name is
                //get their name
                //set name to the client handler
                //get the protocol statement
            } catch (IOException e) {
                e.printStackTrace();
            }


            //TODO: client tells u what they want and u serve it
            // change friendsActivityArea.isAskedForLastListened()
            // the client request also needs a buffered stream reading

//            if( FriendsActivityArea.isAskedForLastListened() ) {
            if( lastListenedIsWantedFlag) {
                String lastListenedSongPath="";
                try {
                   lastListenedSongPath = processRequest();
                   if(lastListenedSongPath.equals("") || lastListenedSongPath==null){
                        client.close();
                        numberOfClients--;
                        return;
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendFile(fis, bis, os, lastListenedSongPath);

            }else{
                //asked for the shared playlist
                //get the file name for shared playlist here
                if(!new File("src/SharedPlaylist.txt").exists()){
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    numberOfClients--;
                    return;
                }
                try {
                    Scanner sc = new Scanner(new FileReader("src/SharedPlaylist.txt"));
                    while (sc.hasNext()){
                        sendFile(fis, bis, os, sc.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


            }

        }

        public boolean isLastListenedIsWantedFlag() {
            return lastListenedIsWantedFlag;
        }

        public void setLastListenedIsWantedFlag(boolean lastListenedIsWantedFlag) {
            this.lastListenedIsWantedFlag = lastListenedIsWantedFlag;
        }

        public int getClientId() {
            return clientId;
        }

        public void setClientId(int clientId) {
            this.clientId = clientId;
        }

        private void sendFile(FileInputStream fis, BufferedInputStream bis, OutputStream os, String path ){

            try {
                File myFile = new File(path);
                byte[] mybytearray = new byte[(int) myFile.length()];

                fis = new FileInputStream(myFile);
                bis = new BufferedInputStream(fis);
                bis.read(mybytearray, 0, mybytearray.length);
                os = client.getOutputStream();

                System.out.println("Sending " + path + "(" + mybytearray.length + " bytes)");
                os.write(mybytearray, 0, mybytearray.length);
                os.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    numberOfClients--;
                    if (bis != null) bis.close();
                    if (os != null) os.close();
                    if (client != null) client.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
