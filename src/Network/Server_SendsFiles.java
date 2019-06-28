package Network;

import Logic.Main;
import Logic.Music;
import Logic.PlayerManager;
import GUI.*;
import javazoom.jl.player.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server_SendsFiles implements Runnable {
    private ServerSocket serverSocket;
    ExecutorService executorService;
    private boolean isRun;
    private static int numberOfClients = 0;
    private static int lastClientId = 0;

    public Server_SendsFiles(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
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
//        PlayerManager.getsP() != null;
        if (!Main.isJpotifyGUIWindowClosed()) {
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
        private String clientName;
        private boolean lastListenedIsWantedFlag = true;


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

                String protocolCommand = inp.readLine();
                int localFlag = 0;
                while (localFlag == 0) {
                    if (protocolCommand.startsWith("start")) {
                        clientName = protocolCommand.substring(protocolCommand.indexOf("start") + 6);
                        localFlag = 1;
                    } else out.println("start <yourName>");
                }
                System.out.println("heh that thing is called " + clientName);

                while (true) {
                    protocolCommand = inp.readLine();
                    if (protocolCommand.contains("quit")) {
                        System.out.println(clientId + clientName + " went off!!!");
                        break;//or break?
                    }

                    if (protocolCommand.contains("get")) {
                        System.out.println("got");
                        if (protocolCommand.contains("file")) {
                            if (protocolCommand.contains("--LastListened")) {
                                String lastListenedSongPath = "";
                                try {
                                    lastListenedSongPath = processRequest();
                                    if (lastListenedSongPath.equals("") || lastListenedSongPath == null) {
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
                            } else if (protocolCommand.contains("--SharedPlaylist")) {
                                if (!new File("src/SharedPlaylist.txt").exists()) {
                                    try {
                                        client.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    numberOfClients--;
                                    return;
                                }
                                int count = 0;
                                try {
                                    Scanner sc = new Scanner(new FileReader("src/SharedPlaylist.txt"));
                                    while (sc.hasNext()) {
                                        sc.nextLine();
                                        count++;
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                out.println(count);
                                try {
                                    Scanner sc = new Scanner(new FileReader("src/SharedPlaylist.txt"));
                                    while (sc.hasNext()) {
                                        sendFile(fis, bis, os, sc.nextLine());
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                out.println("Invalid command");
                                out.println("See 'get --help'.");
                            }
                        } else {
                            if (protocolCommand.contains("--lastListened")) {
//                                System.out.println(PlayerManager.getsP().getFileName());
//                                if (Main.getCurrentQueue().get(Main.getSongQueueIndex())!=null) {
//                                if (PlayerManager.getsP().getPlayerStatus().equals("PLAYING")) {
                                if (protocolCommand.contains("title")) {
                                    if (!Main.isJpotifyGUIWindowClosed()) {
//                                        System.out.println("in here1");
                                        out.println(Main.getCurrentQueue().get(Main.getSongQueueIndex()).getSongName());
                                        out.println("now");
//                                        System.out.println("sent");

                                    } else {
//                                        System.out.println("in here 2");
                                        try {
                                            Scanner sc = new Scanner(new FileReader("src/LastSongListened.txt"));
                                            Music music = new Music(sc.nextLine());
                                            out.println(music.getSongData().getSongName());
                                        } catch (FileNotFoundException e) {
                                            out.println("An error occurred while fetching file name.");
                                        }
                                    }

                                }
                                else if (protocolCommand.contains("time")) {
                                    Date dateListened = new Date(new File("src/LastSongListened.txt").lastModified());

                                    //TODO WIFE
                                    String diff = "get it from my koose mahi";
                                    out.println(diff);
                                }

                            } else if (protocolCommand.contains("--sharedPlaylist")) {
                                if (!new File("src/SharedPlaylist.txt").exists()) {
                                    try {
                                        client.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    numberOfClients--;
                                    return;
                                }
                                int count = 0;
                                try {
                                    Scanner sc = new Scanner(new FileReader("src/SharedPlaylist.txt"));
                                    while (sc.hasNext()) {
                                        sc.nextLine();
                                        count++;
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                out.println(count);
                                try {
                                    Scanner sc = new Scanner(new FileReader("src/SharedPlaylist.txt"));
                                    while (sc.hasNext()) {
                                        out.println(sc.nextLine());
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            } else if (protocolCommand.contains("--help")) {
                                out.println("HELP:");
                                //TODO guide the lost sheep to salivation .
                                out.println("These are common commands used in various situations:");
                                out.println("get    get data or file using appropriate switches.");
                                out.println("quit");
                                out.println("start <name>");
                                out.println("some switches: --myID, --myName, --LastListened, --sharedPlaylist, --help");

                            } else if (protocolCommand.contains("--myName")) {
                                out.println(clientName);
                            } else if (protocolCommand.contains("--yourName")) {
                                out.println(Main.getMyName());
                            } else if (protocolCommand.contains("--myID")) {
                                out.println(clientId);
                            } else {
                                out.println("Invalid command");
                                out.println("See 'get --help'.");
                            }
                        }
                    } else {
                        out.println("Invalid command");
                        out.println("See 'get --help'.");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

        private void sendFile(FileInputStream fis, BufferedInputStream bis, OutputStream os, String path) {

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
            } finally {
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

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }
    }


    public static void main(String[] args) {
//        try {
//            Main.server_sendsFiles.run();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
