import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws Exception {
        // CNNScaper is a class
        // An instance of a class is an object
        // Java is an object oriented language (oo paradigm)

//        CNNscraper example = new CNNscraper();
//        CNNscraper.link = "blah";
//
//        CNNscraper example2 = new CNNscraper();
//        example2.link = "blah2";

        // link can be changed externally
        // link is "scoped" to each object, not shared

        CNNscraper scraper = new CNNscraper();
        //System.out.println(scraper.builder());
       scraper.getHeadlines(scraper.builder()).forEach(System.out::println);
    }
}
