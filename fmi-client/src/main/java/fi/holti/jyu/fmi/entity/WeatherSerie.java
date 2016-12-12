package fi.holti.jyu.fmi.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WeatherSerie {

	@JacksonXmlProperty(localName = "Time")
	private String timestamp;
	@JacksonXmlProperty(localName = "ParameterName")
	private String measureName;
	@JacksonXmlProperty(localName = "ParameterValue")
	private BigDecimal measureValue;
	private String location;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}

	public BigDecimal getMeasureValue() {
		return measureValue;
	}

	public void setMeasureValue(BigDecimal measureValue) {
		this.measureValue = measureValue;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
