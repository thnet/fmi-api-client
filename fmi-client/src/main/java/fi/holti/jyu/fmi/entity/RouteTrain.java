package fi.holti.jyu.fmi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RouteTrain {

	private String routetrainNumber;
	private String endStationShortCode;

	public boolean isLessThan(int trainNumber) {
		return Integer.parseInt(routetrainNumber) < trainNumber;
	}

	public String getRoutetrainNumber() {
		return routetrainNumber;
	}

	public void setRoutetrainNumber(String routetrainNumber) {
		this.routetrainNumber = routetrainNumber;
	}

	public String getEndStationShortCode() {
		return endStationShortCode;
	}

	public void setEndStationShortCode(String endStationShortCode) {
		this.endStationShortCode = endStationShortCode;
	}

}
