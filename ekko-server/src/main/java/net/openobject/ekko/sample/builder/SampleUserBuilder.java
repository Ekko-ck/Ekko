package net.openobject.ekko.sample.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.openobject.ekko.sample.dto.SampleUserResponse;
import net.openobject.ekko.sample.dto.SampleUserRequest;
import net.openobject.ekko.sample.entity.SampleUser;

@Component
public class SampleUserBuilder {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<SampleUserResponse> buildDtoList(List<SampleUser> entityList) {
		return entityList.stream()
			.map(entity -> modelMapper.map(entity, SampleUserResponse.class))
			.collect(Collectors.toList());
	}
	
	public SampleUserResponse buildDto(SampleUser entity) {
		if (entity == null) {
			return null;
		}
		SampleUserResponse dto = modelMapper.map(entity, SampleUserResponse.class);
		return dto;
	}
	
	public SampleUser buildEntity(SampleUserResponse dto) {
		if (dto == null) {
			return null;
		}
		SampleUser entity = SampleUser.builder()
								.userId(dto.getUserId())
								.userName(dto.getUserName())
								.build();
		return entity;
	}
	
	public SampleUser buildEntityForRegister(SampleUserRequest formDto) {
		if (formDto == null) {
			return null;
		}
		SampleUser entity = SampleUser.builder()
								.userId(formDto.getUserId())
								.userName(formDto.getUserName())
								.build();
		entity.setAuditColumnsToDefault();
		return entity;
	}
	
}
