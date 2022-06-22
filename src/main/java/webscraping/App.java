package webscraping;

public class App {

    public static void main(String[] args) throws Exception {
        CNNscraper scraper = new CNNscraper();
        //System.out.println(scraper.builder());
        scraper.getHeadlines(scraper.builder()).forEach(System.out::println);
    }
}
