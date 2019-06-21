package Logic;

import javazoom.jl.decoder.JavaLayerException;

public class Player {
    private static SongPlayer sP;

    public Player(){
        try {
            sP = new SongPlayer("/home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3");
            //Mahvash:  :/AUT/Term2/JpotifyFinalProject/songs/DeanLewis.mp3
            //Niki: /home/niki/Desktop/ailee - i will show you my-free-mp3s.com .mp3
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static SongPlayer getsP() {
        return sP;
    }
}
