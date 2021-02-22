
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Geocoder {

    private static double latlat;
    private static double lnglng;

    private static final String GEOCODING_RESOURCE = "https://geocode.search.hereapi.com/v1/geocode";
    private static final String API_KEY = "uY8aNon1m_rtZ6pGf_xI3bqOs-na97VWM0Y1rFUSI_M";

    public String GeocodeSync(String query) throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        String encodedQuery = URLEncoder.encode(query,"UTF-8");
        String requestUri = GEOCODING_RESOURCE + "?apiKey=" + API_KEY + "&q=" + encodedQuery;

        HttpRequest geocodingRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();

        HttpResponse geocodingResponse = httpClient.send(geocodingRequest,
                HttpResponse.BodyHandlers.ofString());

        return (String) geocodingResponse.body();
    }

    public static LatLong decode(String location) throws IOException, InterruptedException {
        Geocoder test = new Geocoder();
        ObjectMapper mapper = new ObjectMapper();

        String response = test.GeocodeSync(location);

        JsonNode responseJsonNode = mapper.readTree(response);
        JsonNode items = responseJsonNode.get("items");


        for (JsonNode item : items) {
            JsonNode position = item.get("position");

            String lat = position.get("lat").asText();
            String lng = position.get("lng").asText();
            latlat = Double.parseDouble(lat);
            lnglng = Double.parseDouble(lng);
        }

        return new LatLong(latlat,lnglng);
    }

}

