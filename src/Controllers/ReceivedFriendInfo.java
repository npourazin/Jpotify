package Controllers;

import java.util.ArrayList;

public class ReceivedFriendInfo {
    private static String friendName;

    public static void setFriendName(String friendName) {
        ReceivedFriendInfo.friendName = friendName;
    }

    public static void setLastSong(String lastSong) {
        ReceivedFriendInfo.lastSong = lastSong;
    }

    public static void setLastTimeListened(String lastTimeListened) {
        ReceivedFriendInfo.lastTimeListened = lastTimeListened;
    }

    private static String lastSong;

    public static String getFriendName() {
        return friendName;
    }

    public static String getLastSong() {
        return lastSong;
    }

    public static String getLastTimeListened() {
        return lastTimeListened;
    }

    private static String lastTimeListened;


    public ReceivedFriendInfo(String name,String lastSong,String lastTimeListened){
        this.friendName =name;
       this.lastSong=lastSong;
       this.lastTimeListened=lastTimeListened;
    }







}
