import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {

        //System.out.println("Hello, World!");

        //fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        //extrair só os dados que interessam (título, poster, classificação)

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //System.out.println(listaDeFilmes.get(0));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(body);

        //exibir e manipular os dados
        for (JsonNode node : jsonNode.get("items")) {
            System.out.println(node.get("title").asText());
            System.out.println(node.get("image").asText());
            System.out.println(node.get("imDbRating").asText());
            System.out.println();
        }
    }
}
