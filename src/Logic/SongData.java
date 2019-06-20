package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class SongData extends Mp3File implements Serializable {
    private File file;
    private String absolutePath;
    private Date lastTimeListened;
    private int pausedOnFrame = 0;
    private String artist;
    private String songName;
    private String album;
    private int genre;
    private Icon icon;
    private final Date timeItWasAdded;

    public SongData(String filename, Date date) throws IOException, UnsupportedTagException, InvalidDataException {
        super(filename);
        absolutePath = filename;
        timeItWasAdded =date;
        lastTimeListened = new Date(0);
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public int getPausedOnFrame() {
        return pausedOnFrame;
    }

    public void setPausedOnFrame(int pausedOnFrame) {
        this.pausedOnFrame = pausedOnFrame;
    }

    public Date getLastTimeListened() {
        return lastTimeListened;
    }

    public void setLastTimeListened(Date lastTimeListened) {
        this.lastTimeListened = lastTimeListened;
    }

    public Date getTimeItWasAdded() {
        return timeItWasAdded;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}























//   public void playSong(){
//        FileInputStream fis = null;
//        try{
//            fis = new FileInputStream("/home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3");
//            Player playMP3 = new Player(fis);
//            playMP3.play();
//            AdvancedPlayer player = new AdvancedPlayer(fis);
//            player.setPlayBackListener(new PlaybackListener() {
//                @Override
//                public void playbackFinished(PlaybackEvent event) {
//                    pausedOnFrame = event.getFrame();
//                }
//            });
//            player.play();
//        }
//        catch(Exception exc){
//            exc.printStackTrace();
//            System.out.println("Failed to play the file.");
//        }
//
//    }