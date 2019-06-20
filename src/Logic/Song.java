package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

public class Song extends Mp3File implements Serializable {
    private static Thread songPlayThread;
    private final String fileName;
    private File file;
    String absolutePath;
    private Calendar lastTimeListened;
    //Playing Fields
    private int pausedOnFrame = 0;
//    private final Calendar timeItWasAdded;

//    Song

    public Song(String filename) throws IOException, UnsupportedTagException, InvalidDataException {
        super(filename);
        this.fileName=filename;
    }


//    public void playSong(){
//        songPlayThread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                FileInputStream fis = null;
//                try{
//                    fis = new FileInputStream(fileName );
//                    Player playMP3 = new Player(fis);
//                    playMP3.play();
//                    AdvancedPlayer player = new AdvancedPlayer(fis);
//                    player.setPlayBackListener(new PlaybackListener() {
//                        @Override
//                        public void playbackFinished(PlaybackEvent event) {
//                            pausedOnFrame = event.getFrame();
//                        }
//                    });
//                    player.play();
//                }
//                catch(Exception exc){
//                    exc.printStackTrace();
//                    System.out.println("Failed to play the file.");
//                }
//            }
//        });


//    }
//    public static Thread getSongPlayThread(){
//        return songPlayThread;
//    }

}
