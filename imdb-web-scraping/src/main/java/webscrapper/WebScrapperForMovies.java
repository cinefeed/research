package webscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScrapperForMovies {
    public static void main(String[] args) throws IOException {

        String movieId = "tt4154756";
        String url = "https://m.imdb.com/title/" + movieId + "/fullcredits/cast";
        Document doc = Jsoup.connect(url)
                .header("Accept-Language", "en-US,en;q=0.9,el;q=0.8")
                .userAgent("Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; " +
                        "wv) AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36")
                .get();
        Elements contents = doc.getElementsByClass("btn-full subpage");

        for (Element content : contents) {
            String nameId = content.attr("href");
            String name = content.getElementsByTag("h4").text();
            String character = content.getElementsByTag("p").text();

            System.out.println("nameId: " + nameId.replaceFirst("/.*/(.*)/-?.*$","$1") + " | "
                    + "name: " + name + " | " + "character: " + character);
        }
    }
}
