package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

public class Song extends Mp3File {
    public Song(String filename) throws IOException, UnsupportedTagException, InvalidDataException {
        super(filename);
    }

    public void playSong(){
        System.out.println(this.getLayer());
    }
}
