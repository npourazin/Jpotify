package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

public class Music {
    private Mp3File mp3File;
    private String absolutePath;
    private SongData songData;
    public Music(String absolutePath){
        this.absolutePath = absolutePath;
        try {
            mp3File = new Mp3File(absolutePath);
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
        try {
            songData =new SongData(absolutePath, Date.from(Instant.now()));
            catchData();
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
    }

    public Music(SongData songData){
        this.songData = songData;
        try {
            mp3File = new Mp3File(songData.getAbsolutePath());
            catchData();
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    private void catchData(){
        if(mp3File==null) return;
        if(mp3File.hasId3v1Tag()){
            songData.setSongName(mp3File.getId3v1Tag().getTitle());
            songData.setAlbum(mp3File.getId3v1Tag().getAlbum());
            songData.setArtist(mp3File.getId3v1Tag().getArtist());
            songData.setGenre(mp3File.getId3v1Tag().getGenre());
        }
        else if(mp3File.hasId3v2Tag()){
            songData.setSongName(mp3File.getId3v2Tag().getTitle());
            songData.setAlbum(mp3File.getId3v2Tag().getAlbum());
            songData.setArtist(mp3File.getId3v2Tag().getArtist());
            songData.setGenre(mp3File.getId3v2Tag().getGenre());
            byte[] imageBytes = mp3File.getId3v2Tag().getAlbumImage();
            try {
                if(imageBytes!=null){
                    Image img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                    Icon icon = new ImageIcon(img);
                    songData.setIcon(icon);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void playSong(){
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(songData.getAbsolutePath());
            Player playMP3 = new Player(fis);
            playMP3.play();
            AdvancedPlayer player = new AdvancedPlayer(fis);
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    songData.setPausedOnFrame(event.getFrame());
                }
            });
            //here we have Stream closed Exception when the son finishes
            //manage it
            player.play();
        }
        catch(Exception exc){
            exc.printStackTrace();
            System.out.println("Failed to play the file.");
        }

    }

    public void setSongData(SongData songData) {
        this.songData = songData;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
