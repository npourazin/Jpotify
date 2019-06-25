package Logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class SongPlayer implements Serializable {
    private Status playerStatus;
    private String fileName;
    private Thread t = null;

    // the player actually doing all the work
    private AdvancedPlayer player;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    enum Status {
        NOTSTARTED, PLAYING, PAUSED, FINISHED;
    }

    public SongPlayer(String fileName) throws JavaLayerException {
        playerStatus = Status.NOTSTARTED;
        this.fileName = fileName;
        FileInputStream file ;
        try {
            file = new FileInputStream(this.fileName);
            player = new AdvancedPlayer(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to play the file.");
        }
    }

    public void playSong() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            playInternal();
                        }
                    };
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = Status.PLAYING;
                    t.start();
                    break;
                case PAUSED:
                    resumeSong();
                    break;
                default:
                    break;
            }
        }
    }

    public boolean pauseSong() {
        synchronized (playerLock) {
            if (playerStatus == Status.PLAYING) {
                playerStatus = Status.PAUSED;
            }
            return playerStatus == Status.PAUSED;
        }
    }

    public boolean resumeSong() {
        synchronized (playerLock) {
            if (playerStatus == Status.PAUSED) {
                playerStatus = Status.PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == Status.PLAYING;
        }
    }

    public void stopSong() {
        synchronized (playerLock) {
            playerStatus = Status.FINISHED;
            playerLock.notifyAll();
        }
    }

    private void playInternal() {
        while (playerStatus != Status.FINISHED) {
            try {
                if (!player.decodeFrame()) {
                    break;
                }
            } catch (final JavaLayerException e) {
                System.out.println("EX");
                break;
            }

            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == Status.PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }
        close();
    }

    public void close() {
        synchronized (playerLock) {
            playerStatus = Status.FINISHED;
        }
        try {
            player.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }

    public boolean ifPlayerNotstarted() {
        if (playerStatus == Status.NOTSTARTED)
            return true;
        return false;
    }

    //play the song from a specific frame.
    public void playInMiddle(int start) {

        //because we need to jump to the first frame , so we recreate the the file.
        FileInputStream file = null;
        try {
            file = new FileInputStream(this.fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            player = new AdvancedPlayer(file);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }


        //jumping to the wanted frame.
        boolean ret = true;
        int offset = start;
        while (offset-- > 0 && ret) {
            try {
                ret = player.skipFrame();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
        resumeSong();
    }

    public String getPlayerStatus(){
        return playerStatus.toString();
    }

    public String getFileName() {
        return fileName;
    }

    public void setVolume() throws InvalidDataException, IOException, UnsupportedTagException {
//        (player.getGainControl()).setLevel((float)GUI.getVolumeSlider().getValue() / 150.0f);
        //set volume

        SourceDataLine source =null;
        Mp3File mp3File = new Mp3File(this.getFileName());
        source = (SourceDataLine) mp3File;
        FloatControl volControl = (FloatControl) source.getControl(FloatControl.Type.MASTER_GAIN);
        if (volControl != null) {
            //0.73F -> random!!!!!!!
            float newGain = Math.min(Math.max(0.73F, volControl.getMinimum()), volControl.getMaximum());
            volControl.setValue(newGain);
        }
    }

}
