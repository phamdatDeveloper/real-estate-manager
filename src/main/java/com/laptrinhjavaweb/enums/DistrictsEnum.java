package com.laptrinhjavaweb.enums;

public enum DistrictsEnum {
	QUAN_1("Quận 1"), QUAN_2("Quận 2");

	private final String districtValue;

	DistrictsEnum(String districtValue) {
		this.districtValue = districtValue;
	}

	public String getDistrictValue() {
		return districtValue;
	}

}
