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
    //todo fill this


    private Socket socket;
    private int ID;

    Client_ReceivesFiles(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
    }

    public Socket getSocket() {
        return socket;
    }

//    public void fetchData() {
//
//    }

    //    public final static int SOCKET_PORT = 8080;
    public final static String SERVER = "127.0.0.1";  // localhost

//    private static File dir = new File("src/RECEIVED");
//    private static List<File> files = Arrays.asList(dir.listFiles());
//    private static int id = files.size() + 1;

    //TODO create different folders for different clients in RECEIVED;
    // you may change this, I give a
    // different name because i don't want to
    // overwrite the one used by server...

    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    // should bigger than the file to be downloaded

    private static Client_ReceivesFiles client_receivesFiles;

    static {
        try {
            client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {

//        Client_ReceivesFiles client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);

        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);

//        out.println("start " + Main.getMyName());
//        out.println("get --myID");
//        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));

        //return the number of files i shall get?
        //just check what i wrote?
        //use how it was called?

        getLastListenedData();

//
//        out.println("quit");
//        client_receivesFiles.getSocket().close();

    }

    public static void receiveFile() {

        File dir = new File("src/RECEIVED" + client_receivesFiles.getID() + "/");
        List<File> files = Arrays.asList(dir.listFiles());
        int id = files.size() + 1;
        String FILE_TO_RECEIVED = "src/RECEIVED/" + client_receivesFiles.getID() + "/" + id + ".mp3";


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

    public static void readAFile() throws IOException {
//        Client_ReceivesFiles client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);
        prepareReceivedFilesDestination();

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

    public static void deleteDirectoryRecursionJava6(File file) throws IOException {
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

    private static void prepareReceivedFilesDestination() throws IOException {

        File file = new File("src/RECEIVED/" + client_receivesFiles.getID() + "/");
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

    public static void readMoreThanOneFiles() throws IOException {
//        Client_ReceivesFiles client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);
        prepareReceivedFilesDestination();

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

    public static void getLastListenedData() throws IOException {

        BufferedReader inp = new BufferedReader(new InputStreamReader(client_receivesFiles.getSocket().getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(client_receivesFiles.getSocket().getOutputStream()), true);

        out.println("start " + Main.getMyName());
        out.println("get --myID");
        client_receivesFiles.setID(Integer.parseInt(inp.readLine()));

        out.println("get --lastListened");

        String songName = inp.readLine();
        System.out.println(songName);
        String lastDate = inp.readLine();
        System.out.println(lastDate);

        //TODO WIFE
        //TODO mahvaaaaaaaaaaaaaaaaaaaaaaaaaash take theseeeeeeeeeeeeeeeeee
        // the two strings above are the pieces of data that must be shown besides the server's button

        out.println("get --yourName");
        String name = inp.readLine();
        System.out.println(name);

        //TODO WIFE
        // also the name string above is the server's name
        // make a button with these for the friends panel

        //TODO WIFE
        // this method must be called each time you refresh FriendActivity section.

        out.println("quit");
        client_receivesFiles.getSocket().close();

    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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