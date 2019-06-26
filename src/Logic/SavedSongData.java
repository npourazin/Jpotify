package Logic;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class SavedSongData {

    public static void readFromFile(  ObjectInputStream objIn ){
        ArrayList<SongData> tmpArr = new ArrayList<>();
        if(objIn==null) return;
        try {
            while(true)
                tmpArr = (ArrayList<SongData>) objIn.readObject();
        } catch (EOFException e){

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PlayerManager.setSongDataArrayList(tmpArr);
    }

    public static void writeToFile(ObjectOutputStream objOut, ArrayList<SongData> songDataArrayList){
        try {
           objOut.writeObject(songDataArrayList);
           objOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToFile(ObjectOutputStream objOut, String absolutePath1){
        if(absolutePath1==null) return;
        
        Music music = new Music(absolutePath1);
        music.getSongData().setLastTimeListened(new Date(0));
        
        PlayerManager.getSongDataArrayList().add(music.getSongData());
        //todo songs queue?
        PlayerManager.getSongDataArrayList().sort(new SortByTime());

        System.out.println("PlayerManager.getSongDataArrayList().size() = " + PlayerManager.getSongDataArrayList().size());
        
        writeToFile(objOut, PlayerManager.getSongDataArrayList());
    }
}


