package fi.holti.jyu.fmi.service.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import fi.holti.jyu.fmi.entity.FMIMeasurementTimeSeries;
import fi.holti.jyu.fmi.entity.parameters.MeasurementParameter;

// Mockito Runner for JUnit.
@RunWith(MockitoJUnitRunner.class)
public class FMIMeasurementServiceImplTest {

	// mock out the class
	@Mock
	private RestTemplate restTemplate;
	// inject the mock into instance
	@InjectMocks
	private FMIMeasurementServiceImpl fmiMeasurementServiceImpl = new FMIMeasurementServiceImpl();

	@Test
	public void getsTimeSeries() {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(2015, Calendar.JANUARY, 1, 0, 0, 00);

		Date start = calendar.getTime();

		calendar.set(2015, Calendar.DECEMBER, 31, 23, 00, 00);

		Date end = calendar.getTime();
		String expectedQuery = "http://data.fmi.fi/fmi-apikey/a29d674d-a76d-4dd8-a778-f98292c91c8d/wfs?request=getFeature&storedquery_id=fmi::observations::weather::daily::simple&fmisid=100971&parameters=tday&starttime=2015-01-01T00:00:00Z&endtime=2015-12-31T23:00:00Z";
		FMIMeasurementTimeSeries fmiMeasurementTimeSeries = new FMIMeasurementTimeSeries();
		when(restTemplate.getForObject(anyString(), eq(FMIMeasurementTimeSeries.class)))
				.thenReturn(fmiMeasurementTimeSeries);

		fmiMeasurementServiceImpl.getWeather("100971", MeasurementParameter.tday, start, end);

		verify(restTemplate).getForObject(expectedQuery, FMIMeasurementTimeSeries.class);
	}
}
