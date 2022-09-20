package training.weather;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherForecast {


    public String getCityWeather(String city, Date datetime) throws IOException {
        if (datetime == null) {
            datetime = new Date();
        }
        if (datetime.before(new Date(new Date().getTime() + (1000 * 60 * 60 * 24 * 6)))) {

            String[] coordinates = parseGeocode(city);
            String forecast = callOpenMeteo(coordinates, datetime);

            JSONArray dailyResults = new JSONObject(forecast).getJSONObject("daily").getJSONArray("time");
            JSONArray weatherCodeResults = new JSONObject(forecast).getJSONObject("daily").getJSONArray("weathercode");
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
        else {
            return "Error with provided date: no forecast available yet.";
        }
        return "";
    }

    public static String[] parseGeocode (String city) { //Parses a String city name via geocode.xyz and outputs an array with latitude and longitude
        /*HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
        HttpRequest req = rf.buildGetRequest(new GenericUrl("https://geocode.xyz/" + city + "?json=1"));
        String result = req.execute().parseAsString();*/

        String result = "{   \"standard\" : {      \"addresst\" : {},      \"statename\" : {},      \"city\" : \"Madrid\",      \"prov\" : \"ES\",      \"countryname\" : \"Spain\",      \"postal\" : {},      \"confidence\" : \"0.90\"   },   \"longt\" : \"-3.67930\",   \"alt\" : {      \"loc\" : [         {            \"longt\" : \"-3.64538748232578\",            \"prov\" : \"Comunidad de Madrid\",            \"city\" : \"Madrid\",            \"postal\" : \"28017\",            \"score\" : \"6931\",            \"latt\" : \"40.4288161347569\"         },         {            \"longt\" : \"-3.64539\",            \"prov\" : \"ES\",            \"city\" : \"Madrid\",            \"countryname\" : \"Spain\",            \"postal\" : \"28017\",            \"region\" : {},            \"latt\" : \"40.42882\"         }      ]   },   \"elevation\" : {},   \"latt\" : \"40.42955\"}";
        // stored results from previous request to avoid API usage limits during testing

        JSONObject object = new JSONObject(result);
        String[] output = {object.get("latt").toString(), object.get("longt").toString()}; //an array containing lat, long as strings.

        return output;
    }

   public static String callOpenMeteo(String[] coordinates, Date datetime) throws IOException { //Uses Open-Meteo API to get a weather forecast
        HttpRequestFactory rf = new NetHttpTransport().createRequestFactory();
        HttpRequest req = rf.buildGetRequest(new GenericUrl("https://api.open-meteo.com/v1/forecast?latitude=" +
                coordinates[0] + "&longitude=" + coordinates[1] + "&daily=weathercode&current_weather=true&timezone=Europe%2FBerlin"));

        return req.execute().parseAsString();
    }
}
