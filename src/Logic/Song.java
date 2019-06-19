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
    private int pausedOnFrame = 0;

//    private final Calendar timeItWasAdded;

//    Song

    public Song(String filename) throws IOException, UnsupportedTagException, InvalidDataException {
        super(filename);
    }

   public void playSong(){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream("D:\\AUT\\Term2\\JpotifyFinalProject\\songs\\DeanLewis.mp3");
            Player playMP3 = new Player(fis);
            playMP3.play();
            AdvancedPlayer player = new AdvancedPlayer(fis);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });
            player.play();
        }
        catch(Exception exc){
            exc.printStackTrace();
            System.out.println("Failed to play the file.");
        }

    }

}
