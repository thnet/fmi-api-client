package fi.holti.jyu.fmi.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * POJO for presenting one XML "row"
 * 
 * @author timoh
 *
 */
public class FMIMeasurementTimeSerieWrapper {
	@JacksonXmlProperty(localName = "BsWfsElement")
	private WeatherSerie containerElement;

	public WeatherSerie getContainerElement() {
		return containerElement;
	}

	public void setContainerElement(WeatherSerie containerElement) {
		this.containerElement = containerElement;
	}

}
