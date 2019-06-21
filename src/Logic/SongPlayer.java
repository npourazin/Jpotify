package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SongPlayer {
    private Status playerStatus;
    private String fileName;
    private int currentFrame;


    // the player actually doing all the work
    private AdvancedPlayer player;
    private AdvancedPlayer sparePlayer;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    enum Status {
        NOTSTARTED, PLAYING, PAUSED, FINISHED;
    }

    public SongPlayer(String fileName) throws JavaLayerException {
        playerStatus = Status.NOTSTARTED;
        this.fileName = fileName;
        currentFrame=0;
        FileInputStream file = null;
        try {
            file = new FileInputStream(this.fileName);
            player = new AdvancedPlayer(file);
            sparePlayer=new AdvancedPlayer(file);

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

    public boolean ifPlayerNotstarted(){
        if(playerStatus==Status.NOTSTARTED)
            return true;
        return false;
    }
    //TODO: if we wanted to go back in the song there are some ways:1)overwrite the thread and make if not a final field   2)...?
    public void playInMiddle(int start){
        boolean ret = true;
        int offset = start;
        while (offset-- > 0 && ret) {
            try {
                ret = player.skipFrame();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
//        playSong();
    }

    public int getCurrentFrame(){
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame){
        this.currentFrame=currentFrame;
    }
    public String getFileName(){
        return fileName;
    }
    //I dont know
    public int getTotalFrames(){
        int frames=0;
        while(true){
            try {
                if (!sparePlayer.skipFrame()) break;
                frames++;
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }

        }
        return frames;
    }

}
