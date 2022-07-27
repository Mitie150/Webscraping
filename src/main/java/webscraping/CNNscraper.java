package webscraping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

public class CNNscraper {

    private static final String link = "https://lite.cnn.com/en";


    // TODO give this a better method name
    public String builder() throws Exception {
        HttpRequest.Builder webpage = HttpRequest.newBuilder(new URI(link));
        HttpRequest request = webpage.build();

        HttpClient.Builder client = HttpClient.newBuilder();
        return client.build().send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    // Method that emulates a state machine to extract the headlines
    // toaster is a state machine
    // - states
    // - transitions
    // Unplugged, plugged in, not toasting, toasting, bell sound
    // Plugging in: unplugged -> plugged
    // Press down button: not toasting -> toasting
    // Sensor detects done (timer): toasting -> bell sound -> not toasting

    public String extractHeadline(String input) {
        int slashli = input.indexOf("</li>");
        String second = input.substring(0, slashli);
        int link = second.indexOf("<a");
        String third = second.substring(link);
        int aclose = third.indexOf(">");
        String fourth = third.substring(aclose + 1);
        int content = fourth.indexOf("</a>");
        return fourth.substring(0, content);
    }
    // First iteration
    // first iter: garbage..... <li><a href>.......
    // second iter </li><li..... stuff
    public List<String> getHeadlines(String input) throws Exception {
        int ul = input.indexOf("<ul>");
        String current = input.substring(ul);
        ArrayList<String> articles = new ArrayList<>();
        while (!current.isEmpty()) {
            articles.add(StringEscapeUtils.unescapeHtml4(extractHeadline(current)));
            current = current.substring(6);
            int nextPos = current.indexOf("<li>");
            if (nextPos == -1) {
                break;

            }

            current = current.substring(nextPos);
        }

        // Camelcase helloCoolThing java/scala/functional lang (haskell, etc..)
        // Snakecase hello_cool_thing C++/rust/ruby/perl (old school)
        return articles;
    }
}
