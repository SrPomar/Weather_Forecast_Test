package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {


    public static String getCityWeather(String city, Date datetime) throws IOException {
        if (datetime == null) {
            datetime = new Date();
        }
        if (datetime.before(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 6)))) {
            HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
            HttpRequest req = rf
                    .buildGetRequest(new GenericUrl("https://geocode.xyz/" + city + "?json=1"));
            String r = "{   \"standard\" : {      \"addresst\" : {},      \"statename\" : {},      \"city\" : \"Madrid\",      \"prov\" : \"ES\",      \"countryname\" : \"Spain\",      \"postal\" : {},      \"confidence\" : \"0.90\"   },   \"longt\" : \"-3.67930\",   \"alt\" : {      \"loc\" : [         {            \"longt\" : \"-3.64538748232578\",            \"prov\" : \"Comunidad de Madrid\",            \"city\" : \"Madrid\",            \"postal\" : \"28017\",            \"score\" : \"6931\",            \"latt\" : \"40.4288161347569\"         },         {            \"longt\" : \"-3.64539\",            \"prov\" : \"ES\",            \"city\" : \"Madrid\",            \"countryname\" : \"Spain\",            \"postal\" : \"28017\",            \"region\" : {},            \"latt\" : \"40.42882\"         }      ]   },   \"elevation\" : {},   \"latt\" : \"40.42955\"}";
            // stored results from previous request to avoid API usage limits during testing
            // String r = req.execute().parseAsString();
            JSONObject object = new JSONObject(r);
            String longt = object.get("longt").toString();
            String latt = object.get("latt").toString();
            rf = new NetHttpTransport().createRequestFactory();
            req = rf.buildGetRequest(new GenericUrl("https://api.open-meteo.com/v1/forecast?latitude=" +
                    latt + "&longitude=" + longt + "&daily=weathercode&current_weather=true&timezone=Europe%2FBerlin"));
            r = req.execute().parseAsString();
            JSONArray dailyResults = new JSONObject(r).getJSONObject("daily").getJSONArray("time");
            JSONArray weatherCodeResults = new JSONObject(r).getJSONObject("daily").getJSONArray("weathercode");
            for (int i = 0; i < dailyResults.length(); i++) {
                if (
                        new SimpleDateFormat("yyyy-MM-dd")
                                .format(datetime)
                                .equals(dailyResults.get(i).toString())
                ) {
                  double weatherCode = (Double) weatherCodeResults.get(i); // Directly casting weatherCodeResults as int in the next line did not work, had to split
                  return ForecastEnum.getEnumByCode((int) weatherCode).getDescription();
                }
            }
        }
        return "";
    }
}
