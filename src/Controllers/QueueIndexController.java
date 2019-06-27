package Controllers;

import Logic.Main;
import Logic.PlayerManager;

import java.util.Random;

public class QueueIndexController {
    public static void setIndex(int newIndex) {
        if (ShufflePlayButtonListener.isShuffleOn()) {
            Random rand = new Random();
            newIndex= rand.nextInt(Main.getCurrentQueue().size());
        } else {
            if (newIndex >= Main.getCurrentQueue().size()) newIndex = 0;
            else if (newIndex < 0) newIndex = Main.getCurrentQueue().size() - 1;
        }

        Main.setSongQueueIndex(newIndex);
        PlayerManager.playerManager();
    }
}
