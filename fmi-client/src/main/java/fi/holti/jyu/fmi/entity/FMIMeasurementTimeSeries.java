package fi.holti.jyu.fmi.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Class representing XML root having members as elements
 * 
 * @author timoh
 *
 */
@JacksonXmlRootElement(localName = "FeatureCollection")
public class FMIMeasurementTimeSeries {

	@JacksonXmlProperty(localName = "timeStamp")
	private String timestamp;
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "member")
	private List<FMIMeasurementTimeSerieWrapper> fmiMeasurementTimeSeries = new ArrayList<FMIMeasurementTimeSerieWrapper>();

	public List<FMIMeasurementTimeSerieWrapper> getFmiMeasurementTimeSeries() {
		return fmiMeasurementTimeSeries;
	}

	public void setFmiMeasurementTimeSeries(List<FMIMeasurementTimeSerieWrapper> fmiMeasurementTimeSeries) {
		this.fmiMeasurementTimeSeries = fmiMeasurementTimeSeries;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
