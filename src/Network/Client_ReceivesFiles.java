package Network;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Client_ReceivesFiles {
    //todo fill this


    private Socket socket;

    Client_ReceivesFiles(String ip, int port) throws IOException {
        this.socket = new Socket(ip, port);
    }

    public Socket getSocket() {
        return socket;
    }

    public void fetchData() {

    }

    //    public final static int SOCKET_PORT = 8080;
    public final static String SERVER = "127.0.0.1";  // localhost

    private static File dir = new File("src/RECEIVED");
    private static List<File> files = Arrays.asList(dir.listFiles());
    private static int id = files.size() + 1;

    //TODO create different folders for different clients in RECEIVED;
    public final static String FILE_TO_RECEIVED = "src/RECEIVED/" + id + ".mp3";
    // you may change this, I give a
    // different name because i don't want to
    // overwrite the one used by server...

    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    // should bigger than the file to be downloaded

    public static void main(String[] args) throws IOException {
        Client_ReceivesFiles client_receivesFiles = new Client_ReceivesFiles(SERVER, 8080);

        receiveFile(client_receivesFiles);

    }

    public static void receiveFile(Client_ReceivesFiles client_receivesFiles) {
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