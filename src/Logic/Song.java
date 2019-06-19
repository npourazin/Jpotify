package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Song extends Mp3File {
    private File file;
    String absolutePath;
    private Calendar lastTimeListened;
//    private final Calendar timeItWasAdded;

//    Song

    public Song(String filename) throws IOException, UnsupportedTagException, InvalidDataException {
        super(filename);
    }

    public void playSong(){
        System.out.println(this.getLayer());
    }

}
