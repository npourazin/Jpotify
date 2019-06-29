package Network;

import Logic.Main;

import java.io.*;
import java.net.Socket;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Client_ReceivesFiles {
    private Socket socket;
    private int ID;

    public Client_ReceivesFiles(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
    }

    public static void setClient_receivesFiles(Client_ReceivesFiles client_receivesFiles) {
        Client_ReceivesFiles.client_receivesFiles = client_receivesFiles;
    }

    public Socket getSocket() {
        return socket;
    }


    public static String SERVER = "127.0.0.1";  // localhost
    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    // should bigger than the file to be downloaded

    private static Client_ReceivesFiles client_receivesFiles=Main.getClient_receivesFiles();

//    static {
//        try {
//            client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * makes a directory in RECEIVED folder named as the client's id.
     * writes a single file that eas received into that directory.
     */
    public static void receiveFile() {
        client_receivesFiles=Main.getClient_receivesFiles();
        File dir = new File("src/RECEIVED" + client_receivesFiles.getSocket().getInetAddress() + "/");
        List<File> files = Arrays.asList(dir.listFiles());
        int id = files.size() + 1;
        String FILE_TO_RECEIVED = "src/RECEIVED" + client_receivesFiles.getSocket().getInetAddress() + "/" + id + ".mp3";


        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;

        try {
            sock = client_receivesFiles.getSocket();
            System.out.println("Connected!");
            ///TODO get lastlistened status from my server to update Friends activity UI

            // receive file
            System.out.println();
            byte[] mybytearray = new byte[FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVED);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray, 0, mybytearray.length);
            current = bytesRead;

            do {
                bytesRead =
                        is.read(mybytearray, current, (mybytearray.length - current));
                if (bytesRead >= 0) current += bytesRead;
            } while (bytesRead > -1);

            bos.write(mybytearray, 0, current);
            bos.flush();
            System.out.println("File " + FILE_TO_RECEIVED
                    + " downloaded (" + current + " bytes read)");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (bos != null) bos.close();
                if (sock != null) sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * asks the server to send it a sigle file and receives it.
     * @throws IOException
     */
    public static void readAFile() throws IOException {
        client_receivesFiles=Main.getClient_receivesFiles();

        prepareReceivedFilesDestination("src/RECEIVED" + client_receivesFiles.getSocket().getInetAddress() + "/");

        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);

        out.println("start " + Main.getMyName());
        out.println("get --myID");
        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));

        out.println("get --lastListened");
        receiveFile();

        out.println("quit");
        client_receivesFiles.getSocket().close();
    }

    /**
     * dletes a directory and its contents recusivly.
     * @param file the targeted directory
     * @throws IOException
     */
    public static void deleteDirectoryRecursionJava6(File file) throws IOException {
        client_receivesFiles=Main.getClient_receivesFiles();

        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    deleteDirectoryRecursionJava6(entry);
                }
            }
        }
        if (!file.delete()) {
            throw new IOException("Failed to delete " + file);
        }
    }

    /**
     * if the targeted directory exists, it empties it's contents and otherwise it is created.
     * @param path the targeted directory's path
     * @throws IOException
     */
    public static void prepareReceivedFilesDestination(String path) throws IOException {
        client_receivesFiles=Main.getClient_receivesFiles();

        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
                return;//todo    ???????
            }
        }else{
            deleteDirectoryRecursionJava6(file);
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
                return;//todo    ???????
            }
        }
    }

    /**
     * used to receive several file from the server and save them all.
     * @throws IOException
     */
    public static void readMoreThanOneFiles() throws IOException {
//        Client_ReceivesFiles client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);
        client_receivesFiles=Main.getClient_receivesFiles();

        prepareReceivedFilesDestination("src/RECEIVED" + client_receivesFiles.getSocket().getInetAddress() + "/");

        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);

        out.println("start " + Main.getMyName());
        out.println("get --myID");
        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));

        out.println("get --sharedPlaylist");
        int numberOfFilesRecieved = inp.read();
        for (int i = 0; i < numberOfFilesRecieved; i++) {
            receiveFile();
        }


        out.println("quit");
        client_receivesFiles.getSocket().close();
    }

//    public static void getLastListenedData() throws IOException {
//
//        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
//        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);
//
//        out.println("start " + Main.getMyName());
//        out.println("get --myID");
//        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));
//
//        out.println("get --lastListened");
//
//        String songName = inp.readLine();
//        System.out.println(songName);
//        String lastDate = inp.readLine();
//        System.out.println(lastDate);
//
//        //TODO WIFE
//        //TODO mahvaaaaaaaaaaaaaaaaaaaaaaaaaash take theseeeeeeeeeeeeeeeeee
//        // the two strings above are the pieces of data that must be shown besides the server's button
//
//        out.println("get --yourName");
//        String name = inp.readLine();
//        System.out.println(name);
//
//        //TODO WIFE
//        // also the name string above is the server's name
//        // make a button with these for the friends panel
//
//        //TODO WIFE
//        // this method must be called each time you refresh FriendActivity section.
//
//        out.println("quit");
//        client_receivesFiles.getSocket().close();
//
//    }

    public static String getLastListenedTitle() throws IOException {
        client_receivesFiles=Main.getClient_receivesFiles();

        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);

        out.println("start " + Main.getMyName());
        out.println("get --myID");
        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));

        out.println("get title --lastListened");

        String songName = inp.readLine();

        out.println("quit");
        client_receivesFiles.getSocket().close();

        return songName;
    }

    public static String getLastListenedTime() throws IOException {
        client_receivesFiles=Main.getClient_receivesFiles();

        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);

        out.println("start " + Main.getMyName());
        out.println("get --myID");
        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));

        out.println("get time --lastListened");

        String lastTime = inp.readLine();

        out.println("quit");
        client_receivesFiles.getSocket().close();

        return lastTime+" ago";
    }

    public static String getYourName() throws IOException {
//        if (client_receivesFiles==null) System.out.println("maraz");
        client_receivesFiles=Main.getClient_receivesFiles();
        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);

        out.println("start " + Main.getMyName());
        out.println("get --myID");
        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));

        out.println("get --yourName");

        String name = inp.readLine();

        out.println("quit");
        client_receivesFiles.getSocket().close();

        return name;
    }

    public int getID() {
        client_receivesFiles=Main.getClient_receivesFiles();
        return ID;
    }

    public static Client_ReceivesFiles getClient_receivesFiles() {
        client_receivesFiles=Main.getClient_receivesFiles();
        return client_receivesFiles;
    }

    public void setID(int ID) {

        client_receivesFiles=Main.getClient_receivesFiles();
        this.ID = ID;
    }
}















/*
   public static void main (String [] args ) throws IOException {
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Connecting...");

            // receive file
            byte [] mybytearray  = new byte [FILE_SIZE];
            InputStream is = sock.getInputStream();
            fos = new FileOutputStream(FILE_TO_RECEIVED);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray,0,mybytearray.length);
            current = bytesRead;

            do {
                bytesRead =
                        is.read(mybytearray, current, (mybytearray.length-current));
                if(bytesRead >= 0) current += bytesRead;
            } while(bytesRead > -1);

            bos.write(mybytearray, 0 , current);
            bos.flush();
            System.out.println("File " + FILE_TO_RECEIVED
                    + " downloaded (" + current + " bytes read)");
        }
        finally {
            if (fos != null) fos.close();
            if (bos != null) bos.close();
            if (sock != null) sock.close();
        }
    }
 */














//
//    public static void main(String[] args) throws IOException {
//
////        Client_ReceivesFiles client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);
//
//        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
//        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);
//
////        out.println("start " + Main.getMyName());
////        out.println("get --myID");
////        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));
//
//        //return the number of files i shall get?
//        //just check what i wrote?
//        //use how it was called?
//
//        getLastListenedTime();
//
////
////        out.println("quit");
////        client_receivesFiles.getSocket().close();
//
//    }