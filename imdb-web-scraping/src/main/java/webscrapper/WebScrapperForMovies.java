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

public class WebScrapperForMovies {
    public static void main(String[] args) throws IOException {

        String movieid = "tt4154756";
        String url = "https://m.imdb.com/title/" + movieid + "/fullcredits/cast";
        Document doc = Jsoup.connect(url).get();
        Elements contents = doc.getElementsByClass("media-body media-vertical-align");
        Map<String, List<String>> map = new LinkedHashMap<>();

        for (Element content : contents) {
            String name = content.child(0).text();
            String character = content.child(1).text();

            put(map, "name", name);
            put(map, "character", character);

            System.out.println("name: " + name + " | " + "character: " + character);
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
