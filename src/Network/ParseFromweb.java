package Network;


import Logic.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParseFromweb {
    public static String parser(String s2) throws IOException {
        String s1="";
//        String s2= "stronger+kelly+clarkson";
        String s3="";





        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            //Operating system is based on Windows
            s1 = "https://www.google.com/search?client=firefox-b-d&channel=trow&q=";
            s3 = "+lyrics&ie=utf-8&oe=utf-8";
        }
        else if (os.contains("osx")){
            //Operating system is Apple OSX based
        }
        else if (os.contains("nix") || os.contains("aix") || os.contains("nux")){
            //Operating system is based on Linux/Unix/*AIX
            s1 = "https://www.google.com/search?client=ubuntu&channel=fs&q=";
            s3 = "+lyrics&ie=utf-8&oe=utf-8";
        }




        String s = s1 + s2 + s3;

        Document doc = Jsoup.connect(s).get();
        log(doc.title());

        Element link = doc.select("a").first();

        String text = doc.body().text();

        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            log("%s\n\t%s", headline.attr("title"), headline.absUrl("href"));
        }
        if(text.contains("Search Results Knowledge result")&& text.contains("Source:"))
            return text.substring(text.indexOf("Search Results Knowledge result")+32, text.indexOf("Source:"));
        else
            return "Unfortunately couldn't find any results :( ";
    }

    private static final Pattern urlPattern = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
    Matcher matcher = urlPattern.matcher("foo bar http://example.com baz");
//    while(matcher.find())
//
//    {
//        int matchStart = matcher.start(1);
//        int matchEnd = matcher.end();
//        // now you have the offsets of a URL match
//    }

    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }

    public static String makeURL(String absolutePath) {
        Music music = new Music(absolutePath);
        String s2 = takeSpacesInmiddle(music.getSongData().getSongName().trim() + "+" + music.getSongData().getArtist().trim());
        return s2;
    }

    private static String takeSpacesInmiddle(String s) {
        String sNew = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sNew = sNew + "+";
            } else {
                sNew = sNew + s.charAt(i);
            }
        }
        return sNew;
    }

    public static void main(String[] args) {
        try {
//            parser("stronger+kelly+clarkson");
            System.out.println(parser(makeURL("/home/niki/Downloads/DeanLewis.mp3")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



