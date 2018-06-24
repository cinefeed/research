package webscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebScrapperForActors {
    public static void main(String[] args) throws IOException {

        String actorid = "nm1785339";
        String url = "https://m.imdb.com/name/" + actorid + "/filmotype/actor";
        Document doc = Jsoup.connect(url).get();
        Map<String, List<String>> map = new LinkedHashMap<>();
        Elements contents = doc.getElementsByClass("media-body media-vertical-align");

        for (Element content : contents) {
            String title = content.child(0).text();
            String typeAndReleaseYear = content.child(1).text();
            String character = content.child(2).text();

            put(map, "title", title);
            put(map, "typeAndReleaseYear", typeAndReleaseYear);
            put(map, "character", character);

            System.out.println("title: " + title + " | " + "typeAndReleaseYear: " + typeAndReleaseYear + " | " + "character: " + character);
        }
    }

    public static void put(Map<String, List<String>> map, String key, String value) {
        if (map.get(key) == null) {
            List<String> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        } else {
            map.get(key).add(value);
        }
    }
}
