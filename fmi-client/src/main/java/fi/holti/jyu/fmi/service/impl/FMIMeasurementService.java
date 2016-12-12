package fi.holti.jyu.fmi.service.impl;

import java.util.Date;
import java.util.List;

import fi.holti.jyu.fmi.entity.FMIMeasurementTimeSerieWrapper;
import fi.holti.jyu.fmi.entity.FMIMeasurementTimeSeries;
import fi.holti.jyu.fmi.entity.parameters.MeasurementParameter;

public interface FMIMeasurementService {

	FMIMeasurementTimeSeries getWeather(String location, MeasurementParameter measurementParameter, Date start, Date end);
}
