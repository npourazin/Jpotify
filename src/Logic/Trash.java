package Logic;

import com.mpatric.mp3agic.Mp3File;

import java.io.File;
import java.time.*;
import java.util.*;
import java.sql.Time;


public class Trash {
    private File file;
    String absolutePath;
    private Calendar lastTimeListened;
    private final Calendar timeItWasAdded;

    Trash() {
        Calendar cal = Calendar.getInstance();
//        Tie time = ;
        timeItWasAdded = cal;
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        lastTimeListened = cal;
    }


    public static void main(String[] args) {
//        Time time = new Time(System.currentTimeMillis());
//        System.out.println("\nisBefore6() currentTimeMillis\n" + time);
//
//
//        java.sql.Time time0 = new Time(06, 00, 00);
//        System.out.println("\ntime0=\n" + time0);
//
//
//        System.out.println("\ncompare " + time0.before(time));
//        System.out.println("\ncompare " + time.before(time0));
//
//
//        // Get calendar set to the current date and time
//        Calendar cal = Calendar.getInstance();
//        Calendar cal2 = Calendar.getInstance();
//        System.out.println();
//
//        // Set time of calendar to 18:00
//        cal.set(Calendar.HOUR_OF_DAY, 25);
//        cal.set(Calendar.MINUTE, 0);
////        cal.set(Calendar.SECOND,
//
//        System.out.println(cal.getTimeInMillis());
//        System.out.println(cal2.getTimeInMillis());
//
//        // Check if current time is after 18:00 today
//        boolean afternow = cal.after(cal2);
//
//        if (afternow) {
//            System.out.println("Go home, it's after 6 PM!");
//        } else {
//            System.out.println("Hello!");
//        }
/*
        File dir = new File(".");
        List<File> files = Arrays.asList(dir.listFiles());

//Ascending order
        Collections.sort(files, new FileComparator());
        for (File file : files) {
            System.out.println(file.getName() + "\t" + new Date(file.lastModified()));
        }

//Descending order
        Collections.sort(files, Collections.reverseOrder(new FileComparator()));
        for (File file : files) {
            System.out.println(file.getName() + "\t" + new Date(file.lastModified()));
        }
   */ }
}

