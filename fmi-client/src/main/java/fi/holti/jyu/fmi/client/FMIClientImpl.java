package fi.holti.jyu.fmi.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.holti.jyu.fmi.entity.FMIMeasurementTimeSerieWrapper;
import fi.holti.jyu.fmi.entity.FMIMeasurementTimeSeries;
import fi.holti.jyu.fmi.entity.WeatherSerie;
import fi.holti.jyu.fmi.entity.parameters.MeasurementParameter;
import fi.holti.jyu.fmi.service.impl.FMIMeasurementService;
import fi.holti.jyu.fmi.util.JSONFileWriter;

@Component
public class FMIClientImpl implements FMIClient {
	@Autowired
	private FMIMeasurementService measurementService;

	public void convertFMIXMLToJSONFile() {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2015, Calendar.AUGUST, 10, 00, 00);

		List<WeatherSerie> allTimeSeries = new ArrayList<WeatherSerie>();

		// while (calendar.get(Calendar.YEAR) < 2017) {

		Date start = calendar.getTime();
		// calendar.set(2015, Calendar.DECEMBER, 31, 23, 0);
		// calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23,
		// 00);

		calendar.set(2016, Calendar.FEBRUARY, 7, 00, 0, 0);

		Date end = calendar.getTime();

		String[] locationCodes = { "100971", "100949", "101124" };

		for (String location : locationCodes) {
			FMIMeasurementTimeSeries fmiMeasurementTimeSeries = measurementService.getWeather(location,
					MeasurementParameter.tday, start, end);

			List<WeatherSerie> weatherRowsForLocation = new ArrayList<WeatherSerie>();

			for (FMIMeasurementTimeSerieWrapper timeSerieWrapper : fmiMeasurementTimeSeries
					.getFmiMeasurementTimeSeries()) {
				WeatherSerie weatherSerieItem = timeSerieWrapper.getContainerElement();

				String timestampString = weatherSerieItem.getTimestamp();

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				Calendar tempCalendar = GregorianCalendar.getInstance();
				try {
					Date date = simpleDateFormat.parse(timestampString);
					tempCalendar.setTime(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (tempCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
					System.out.println("Wednesday found");
					weatherSerieItem.setLocation(location);
					weatherRowsForLocation.add(weatherSerieItem);
				}
			}

			allTimeSeries.addAll(weatherRowsForLocation);

		}

		// calendar.add(Calendar.YEAR, 1);
		// }

		String fileName = "weather.json";
		JSONFileWriter.writeObjectsAsJSONFile(allTimeSeries, fileName);
	}
}
