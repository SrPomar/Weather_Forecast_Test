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

	/*@Test
	public void geocode_test() { // Unit test for
		String geocode =
	}*/
}