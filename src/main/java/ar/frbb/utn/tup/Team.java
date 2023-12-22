package ar.frbb.utn.tup;

import ar.frbb.utn.tup.characters.Character;
import ar.frbb.utn.tup.characters.Entity;
import ar.frbb.utn.tup.characters.Hero;
import ar.frbb.utn.tup.characters.Villain;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String user;
    private List<Character> team = new ArrayList<>();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<Character> getTeam() {
        return team;
    }

    public void setTeam(List<Character> team) {
        this.team = team;
    }

    public Team(String user) {
        this.user = user;
    }

    private static CloseableHttpClient unsafeHttpClient;

    static {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            unsafeHttpClient = HttpClients.custom().setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            e.printStackTrace();
        }
    }

    public static CloseableHttpClient getClient() {
        return unsafeHttpClient;
    }

    public static Character getCharacter(int id) {

        String host = "https://superheroapi.com/api/10160234191642472/";
        String charId = Integer.toString(id);
        try{
            CloseableHttpClient creepyClient = getClient();
            Unirest.setHttpClient(creepyClient);

//            String response = "{\n" +
//                    "  \"response\": \"success\",\n" +
//                    "  \"id\": \"70\",\n" +
//                    "  \"name\": \"Batman\",\n" +
//                    "  \"powerstats\": {\n" +
//                    "    \"intelligence\": \"100\",\n" +
//                    "    \"strength\": \"26\",\n" +
//                    "    \"speed\": \"27\",\n" +
//                    "    \"durability\": \"50\",\n" +
//                    "    \"power\": \"47\",\n" +
//                    "    \"combat\": \"100\"\n" +
//                    "  },\n" +
//                    "  \"biography\": {\n" +
//                    "    \"full-name\": \"Bruce Wayne\",\n" +
//                    "    \"alter-egos\": \"No alter egos found.\",\n" +
//                    "    \"aliases\": [\n" +
//                    "      \"Insider\",\n" +
//                    "      \"Matches Malone\"\n" +
//                    "    ],\n" +
//                    "    \"place-of-birth\": \"Crest Hill, Bristol Township; Gotham County\",\n" +
//                    "    \"first-appearance\": \"Detective Comics #27\",\n" +
//                    "    \"publisher\": \"DC Comics\",\n" +
//                    "    \"alignment\": \"good\"\n" +
//                    "  },\n" +
//                    "  \"appearance\": {\n" +
//                    "    \"gender\": \"Male\",\n" +
//                    "    \"race\": \"Human\",\n" +
//                    "    \"height\": [\n" +
//                    "      \"6'2\",\n" +
//                    "      \"188 cm\"\n" +
//                    "    ],\n" +
//                    "    \"weight\": [\n" +
//                    "      \"210 lb\",\n" +
//                    "      \"95 kg\"\n" +
//                    "    ],\n" +
//                    "    \"eye-color\": \"blue\",\n" +
//                    "    \"hair-color\": \"black\"\n" +
//                    "  },\n" +
//                    "  \"work\": {\n" +
//                    "    \"occupation\": \"Businessman\",\n" +
//                    "    \"base\": \"Batcave, Stately Wayne Manor, Gotham City; Hall of Justice, Justice League Watchtower\"\n" +
//                    "  },\n" +
//                    "  \"connections\": {\n" +
//                    "    \"group-affiliation\": \"Batman Family, Batman Incorporated, Justice League, Outsiders, Wayne Enterprises, Club of Heroes, formerly White Lantern Corps, Sinestro Corps\",\n" +
//                    "    \"relatives\": \"Damian Wayne (son), Dick Grayson (adopted son), Tim Drake (adopted son), Jason Todd (adopted son), Cassandra Cain (adopted ward)\\nMartha Wayne (mother, deceased), Thomas Wayne (father, deceased), Alfred Pennyworth (former guardian), Roderick Kane (grandfather, deceased), Elizabeth Kane (grandmother, deceased), Nathan Kane (uncle, deceased), Simon Hurt (ancestor), Wayne Family\"\n" +
//                    "  },\n" +
//                    "  \"image\": {\n" +
//                    "    \"url\": \"https://www.superherodb.com/pictures2/portraits/10/100/639.jpg\"\n" +
//                    "  }\n" +
//                    "}";
//            Object ob = new JSONParser().parse(response);

            HttpResponse<JsonNode> response = Unirest.get(host + charId).asJson();

            // parsing file "JSONExample.json"
            Object ob = new JSONParser().parse(response.getBody().toString());

            // typecasting ob to JSONObject
            JSONObject charData = (JSONObject) ob;
            String name = (String) charData.get("name");

            JSONObject appearance = (JSONObject) charData.get("appearance");
            String race = (String) appearance.get("race");
            String gender = (String) appearance.get("gender");

            JSONObject image = (JSONObject) charData.get("image");
            String imageURL = (String) image.get("url");

            // TO DO: Stats "null" (buscar otro personaje)
            JSONObject powerstats = (JSONObject) charData.get("powerstats");
            // Empty stats, request another Character
            if(powerstats.get("durability").equals("null")) {
                return null;
            }

            Stats stats = new Stats()
                    .setCombat(Integer.parseInt((String) powerstats.get("combat")))
                    .setDurability(Integer.parseInt((String) powerstats.get("durability")))
                    .setStrength(Integer.parseInt((String) powerstats.get("strength")))
                    .setIntelligence(Integer.parseInt((String) powerstats.get("intelligence")))
                    .setSpeed(Integer.parseInt((String) powerstats.get("speed")))
                    .setPower(Integer.parseInt((String) powerstats.get("power")));

            JSONObject bio = (JSONObject) charData.get("biography");
            String alignment = (String) bio.get("alignment");

            Character character = switch (alignment) {
                case "good" -> new Hero(name, race, gender, imageURL, alignment, stats);
                case "bad" -> new Villain(name, race, gender, imageURL, alignment, stats);
                default -> new Entity(name, race, gender, imageURL, alignment, stats);
            };

            System.out.println(character);
            return character;

        } catch(ParseException e){
            e.printStackTrace();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
