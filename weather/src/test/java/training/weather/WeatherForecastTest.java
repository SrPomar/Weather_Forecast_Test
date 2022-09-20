package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void integration_test() { // Tests the entire class, replaces unfinished_test()
		WeatherForecast testForecast = new WeatherForecast();
		String forecast = null;

		try { // Replaced a throws IOException with a try / catch solution
			forecast = testForecast.getCityWeather("Madrid", new Date());
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert forecast != null : "Integration test failed"; // Added an assert statement
		System.out.println(forecast);
	}

	@Test
	public void geocode_test() { // Unit test for parseGeocode();
		String[] coordinates = null;

		try {
			coordinates = WeatherForecast.parseGeocode("Barcelona");
			System.out.println("Sucessfully fetched city coordinates");
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert coordinates != null : "Could not fetch coordinates from geocode.xyz";
	}

	@Test
	public void openmeteo_test() { // Unit test for callOpenMeteo()
		String[] testCoordinates = {"40.42955", "-3.64539"};
		Date testDate = new Date();
		String response = null;

		try {
			response = WeatherForecast.callOpenMeteo(testCoordinates, testDate);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assert response != null : "Could not fetch forecast from Open-Meteo";
	}
}