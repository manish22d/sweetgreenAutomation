package com.SweetGreen.comparator;

public class City implements Comparable<City> {

	String cityName;
	Integer temp;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", temp=" + temp + "]";
	}

	@Override
	public int compareTo(City city) {
		return Math.subtractExact(this.temp, city.temp);
	}
}
