package training.weather;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

public class WeatherForecastTest {

	@Test
	public void unfinished_test() throws IOException {
		String forecast = WeatherForecast.getCityWeather("Madrid", new Date());
		System.out.println(forecast);
	}
}