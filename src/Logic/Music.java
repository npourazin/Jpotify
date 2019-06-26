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
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

public class Music implements Serializable {
    private Mp3File mp3File;
    private String absolutePath;
    private SongData songData;

    public Music(String absolutePath) {
        this.absolutePath = absolutePath;
        try {
            mp3File = new Mp3File(absolutePath);
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
        try {
            songData = new SongData(absolutePath, Date.from(Instant.now()));
            catchData();
            songData.setMusicLength(mp3File.getLengthInSeconds());
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
    }

    public Music(SongData songData) {
        this.songData = songData;
        try {
            mp3File = new Mp3File(songData.getAbsolutePath());
            catchData();
        } catch (IOException | InvalidDataException | UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    private void catchData() {
        if (mp3File == null) return;


        if (mp3File.hasId3v1Tag()) {
            songData.setSongName(mp3File.getId3v1Tag().getTitle());
            songData.setAlbum(mp3File.getId3v1Tag().getAlbum());
            songData.setArtist(mp3File.getId3v1Tag().getArtist());
            songData.setGenre(mp3File.getId3v1Tag().getGenre());


            //Handle empty field
            if (mp3File.getId3v1Tag().getTitle().equals(""))
                songData.setSongName("Unknown");
            if (mp3File.getId3v1Tag().getAlbum().equals(""))
                songData.setAlbum("Unknown");
            if (mp3File.getId3v1Tag().getArtist().equals(""))
                songData.setArtist("Unknown");
            //TODO: getGenre() does not provide a .equals method , handle it

//         if(mp3File.getId3v1Tag().getGenre())

        }
        if (mp3File.hasId3v2Tag()) {
            songData.setSongName(mp3File.getId3v2Tag().getTitle());
            songData.setAlbum(mp3File.getId3v2Tag().getAlbum());
            songData.setArtist(mp3File.getId3v2Tag().getArtist());
            songData.setGenre(mp3File.getId3v2Tag().getGenre());

            //Handle empty field
            if (mp3File.getId3v2Tag().getTitle().equals(""))
                songData.setSongName("Unknown");
            if (mp3File.getId3v2Tag().getAlbum().equals(""))
                songData.setAlbum("Unknown");
            if (mp3File.getId3v2Tag().getArtist().equals(""))
                songData.setArtist("Unknown");
            //TODO: getGenre() does not provide a .equals method , handle it

//         if(mp3File.getId3v2Tag().getGenre())

            byte[] imageBytes = mp3File.getId3v2Tag().getAlbumImage();
            try {
                if (imageBytes != null) {
                    Image img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                    Icon icon = new ImageIcon(img);
                    songData.setIcon(icon);
                }
                //handle empty icon
                else {
                    try {
                        Image img = ImageIO.read(getClass().getResource("images/defaultSongIcon.png"));
                        songData.setIcon(new ImageIcon(img));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void setSongData(SongData songData) {
        this.songData = songData;
    }

    public SongData getSongData() {
        return songData;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
