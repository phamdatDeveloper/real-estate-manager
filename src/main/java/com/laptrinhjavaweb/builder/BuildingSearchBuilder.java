package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {
	private String name;
	private String district;
//	private Integer buildingArea;
//	private Integer numberOfBasement;
	private String street;
	private String ward;
	private String buildingArea;
	private String numberOfBasement;
	private String areaRentFrom;
	private String[] buildingTypes = new String[] {};
	private String costRentFrom;
	private String costRentTo;
	private String areaRentTo;
	private String staffId;
	
	
	
	public String getName() {
		return name;
	}

	public String getDistrict() {
		return district;
	}

	public String getStreet() {
		return street;
	}

	public String getWard() {
		return ward;
	}

	public String getAreaRentFrom() {
		return areaRentFrom;
	}

	public String getAreaRentTo() {
		return areaRentTo;
	}

	/*
	 * public Integer getBuildingArea() { return buildingArea; }
	 * 
	 * public Integer getNumberOfBasement() { return numberOfBasement; }
	 */
	public String getBuildingArea() {
		return buildingArea;
	}

	public String getNumberOfBasement() {
		return numberOfBasement;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	
	public String getCostRentFrom() {
		return costRentFrom;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	
	public String getStaffId() {
		return staffId;
	}



	private BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.district = builder.district;
		this.street = builder.street;
		this.ward = builder.ward;
		this.buildingArea = builder.buildingArea;
		this.numberOfBasement = builder.numberOfBasement;
		this.buildingTypes = builder.buildingTypes;
		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.areaRentFrom = builder.areaRentFrom;
		this.areaRentTo = builder.areaRentTo;
		this.staffId = builder.staffId;
	}

	// Builder Class
	public static class Builder {

		// required parameters
		private String name;
		private String district;
//		private Integer buildingArea;
//		private Integer numberOfBasement;
		private String street;
		private String ward;
		private String buildingArea;
		private String numberOfBasement;
		private String areaRentFrom;
		private String[] buildingTypes = new String[] {};
		private String costRentFrom;
		private String costRentTo;
		private String areaRentTo;
		private String staffId;

		
		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setAreaRentFrom(String areaRentFrom) {
			this.areaRentFrom = areaRentFrom;
			return this;
		}

		public Builder setAreaRentTo(String areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}

		public Builder setBuildingArea(String buildingArea) {
			this.buildingArea = buildingArea;
			return this;
		}

		public Builder setNumberOfBasement(String numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;

		}

		public Builder setDistrict(String district) {
			this.district = district;
			return this;
		}
		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}

		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}
		public Builder setStaffId(String staffId) {
			this.staffId = staffId;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}

		

	}

}
