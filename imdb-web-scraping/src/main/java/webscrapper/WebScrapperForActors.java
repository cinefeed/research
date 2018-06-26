package webscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WebScrapperForActors {
    public static void main(String[] args) throws IOException {

        String actorId = "nm3361199";
        String profession = "actress";
        String url = "https://m.imdb.com/name/" + actorId + "/filmotype/" + profession;
        String userAgent = "Mozilla/5.0 (Linux; Android 7.0; SM-G892A Build/NRD90M; " +
                "wv) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Version/4.0 Chrome/60.0.3112.107 Mobile Safari/537.36";
        Document doc = Jsoup.connect(url)
                .header("Accept-Language", "en-US,en;q=0.9,el;q=0.8")
                .userAgent(userAgent)
                .get();

        Elements contents = doc.getElementsByClass("btn-full subpage");

        for (Element content : contents) {
            String titleId = content.attr("href");
            String title = content.getElementsByTag("span").text();
            String typeAndReleaseYear = content.getElementsByClass("unbold").first().text();
            String character = content.getElementsByTag("p").text();

            if (typeAndReleaseYear.equals(character)) {
                typeAndReleaseYear = "";
            }

            System.out.println("titleUrl: " + titleId.replaceFirst("/.*/(.*)/-?.*$", "$1") + " | "
                    + "" + title + " | " + "typeAndReleaseYear: " + typeAndReleaseYear + " | "+ "character: " + character);
        }
    }
}
