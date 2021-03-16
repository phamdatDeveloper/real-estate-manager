package com.laptrinhjavaweb.enums;

public enum BuildingTypesEnum {
	TANG_TRET("Tầng trêt"),
	NGUYEN_CAN("Nguyên căn"),
	NOI_THAT("Nội thất");

	private final String buildingTypesValue;

	BuildingTypesEnum(String buildingTypesValue) {
		this.buildingTypesValue = buildingTypesValue;
	}

	public String getBuildingTypesValue() {
		return buildingTypesValue;
	}

	
}
