package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SongPlayer {
    Status playerStatus;
    private String fileName;

    // the player actually doing all the work
    private Player player;
    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    enum Status {
        NOTSTARTED, PLAYING, PAUSED, FINISHED;
    }

    public SongPlayer(String fileName) throws JavaLayerException {
        playerStatus = Status.NOTSTARTED;
        this.fileName = fileName;
        FileInputStream file = null;
        try {
            file = new FileInputStream(this.fileName);
            player = new Player(file);

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
                    final Thread t = new Thread(r);
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
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
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

    //Niki: esmesh cherte!!!!!
    public boolean getPlayerStatus(){
        if(playerStatus==Status.NOTSTARTED)
            return true;
        return false;
    }

}
