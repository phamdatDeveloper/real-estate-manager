package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	private IBuildingRepository buildingRepository;
	private BuildingConverter buildingConverter;

	 public BuildingService() {
		buildingRepository = new BuildingRepository();
		buildingConverter = new BuildingConverter();
	}
	
	 //Cach viet cua java 7
	/* @Override
	public List<BuildingDTO> findAll() {
		
		List<BuildingDTO> result = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll();
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO buildingDTO = buildingConverter.convertToDTO(item);
			result.add(buildingDTO);
		}
		return result;
	}*/

	//java 8
	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch,Pageable pageable) {
		
		/*Map<String, Object> properties = new HashMap<>();
		properties.put("name", fieldSearch.getName());
		properties.put("district", fieldSearch.getDistrict());
		if(StringUtils.isNotBlank(fieldSearch.getBuildingArea())) {
		properties.put("buildingarea", Integer.parseInt(fieldSearch.getBuildingArea()));
		}
		if(StringUtils.isNotBlank(fieldSearch.getNumberOfBasement())) {
		properties.put("numberofbasement", Integer.parseInt(fieldSearch.getNumberOfBasement()));
		}*/ 
//		List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties, pageable);
		Map<String, Object> properties = convertToMapProperties(fieldSearch);	
		
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties,pageable,fieldSearch);
		
		return buildingEntities.stream().map(item -> buildingConverter.convertToDTO(item)).collect(Collectors.toList());
	}


	//video 17 phút 1:00:00
	private Map<String, Object> convertToMapProperties(BuildingSearchBuilder fieldSearch) {
		Map<String, Object> properties = new HashMap<>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
				for (Field field : fields) {
					if(!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent")
							&& !field.getName().startsWith("areaRent") && !field.getName().startsWith("staffId")) {
						field.setAccessible(true);
						if(field.get(fieldSearch) instanceof String ) {
							if(field.getName().equals("BuildingArea") || field.getName().equals("numberOfBasement")) {
								if(field.get(fieldSearch) != null && StringUtils.isNotEmpty((String)field.get(fieldSearch))) {
									properties.put(field.getName().toLowerCase(), Integer.parseInt((String) field.get(fieldSearch)));
								}
							}else {
								properties.put(field.getName().toLowerCase(), field.get(fieldSearch));
							}
						} 
					}
				}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		/*properties.put("name", fieldSearch.getName());
		properties.put("district", fieldSearch.getDistrict());
		properties.put("street", fieldSearch.getStreet());
		properties.put("ward", fieldSearch.getWard());
		if(StringUtils.isNotBlank(fieldSearch.getBuildingArea())) {
		properties.put("buildingarea", Integer.parseInt(fieldSearch.getBuildingArea()));
		}
		if(StringUtils.isNotBlank(fieldSearch.getNumberOfBasement())) {
		properties.put("numberofbasement", Integer.parseInt(fieldSearch.getNumberOfBasement()));
		}*/
		return properties;
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity entity = buildingConverter.convertToEntity(buildingDTO);
		entity.setCreatedDate(new Date());
		entity.setCreatedBy("pham dat");
		Long id= buildingRepository.insert(entity);
		//goi den ham findbyid de tra ve doi tuong
//		return buildingConverter.convertToDTO(buildingRepository.save(entity));
		//tam thoi tra ve null
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}

	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districs = new HashMap<>();
		for (DistrictsEnum item : DistrictsEnum.values()) {
			districs.put(item.toString(), item.getDistrictValue())	;
		}
		return districs;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypesValue());
		}
		return buildingTypes;
	}
}
