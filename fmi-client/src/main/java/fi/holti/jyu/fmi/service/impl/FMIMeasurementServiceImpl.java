package fi.holti.jyu.fmi.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fi.holti.jyu.fmi.entity.FMIMeasurementTimeSeries;
import fi.holti.jyu.fmi.entity.parameters.MeasurementParameter;

// Indicate that this class is a service component that is good candidate for cross-cutting concerns and component scanning.
@Service
public class FMIMeasurementServiceImpl implements FMIMeasurementService {
	@Autowired
	private RestTemplate restTemplate;
	private Logger logger = LoggerFactory.getLogger(FMIMeasurementServiceImpl.class);

	private String BASE_URL = "http://data.fmi.fi/fmi-apikey/a29d674d-a76d-4dd8-a778-f98292c91c8d/wfs?request=getFeature&storedquery_id=fmi::observations::weather::daily::simple&";
	private String LOCATION_ID_PARAMETER = "fmisid";
	private String MEASUREMENT_PARAMETERS = "parameters";
	private String START_DATE = "starttime";
	private String END_DATE = "endtime";
	private String QUERY_AND = "&";
	private String QUERY_EQUALS = "=";

	public FMIMeasurementTimeSeries getWeather(String location, MeasurementParameter measurementParameter, Date start,
			Date end) {

		StringBuilder queryBuilder = buildQuery(location, measurementParameter, start, end);

		logger.info("Getting time series by query=" + queryBuilder.toString());

		FMIMeasurementTimeSeries fmiMeasurementTimeSeries = restTemplate.getForObject(queryBuilder.toString(),
				FMIMeasurementTimeSeries.class);

		logger.info("Found " + fmiMeasurementTimeSeries.getFmiMeasurementTimeSeries().size() + " time series");

		return fmiMeasurementTimeSeries;
	}

	private StringBuilder buildQuery(String location, MeasurementParameter measurementParameter, Date start, Date end) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(BASE_URL);
		queryBuilder.append(LOCATION_ID_PARAMETER);
		queryBuilder.append(QUERY_EQUALS);
		queryBuilder.append(location);
		queryBuilder.append(QUERY_AND);
		queryBuilder.append(MEASUREMENT_PARAMETERS);
		queryBuilder.append(QUERY_EQUALS);
		queryBuilder.append(measurementParameter);
		queryBuilder.append(QUERY_AND);
		queryBuilder.append(START_DATE);
		queryBuilder.append(QUERY_EQUALS);
		queryBuilder.append(formatDate(start));
		queryBuilder.append(QUERY_AND);
		queryBuilder.append(END_DATE);
		queryBuilder.append(QUERY_EQUALS);
		queryBuilder.append(formatDate(end));
		return queryBuilder;
	}

	private String formatDate(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return simpleDateFormat.format(date);
	}

}
