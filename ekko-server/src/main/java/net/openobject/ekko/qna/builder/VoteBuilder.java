package net.openobject.ekko.qna.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.qna.dto.VoteDto;
import net.openobject.ekko.qna.dto.VoteRequest;
import net.openobject.ekko.qna.entity.Vote;

@Slf4j
@Component
public class VoteBuilder {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<VoteDto> buildDtoList(List<Vote> entityList) {
		return entityList.stream()
			.map(this::buildDto)
			.collect(Collectors.toList());
	}
	
	public VoteDto buildDto(Vote entity) {
		if (entity == null) {
			return null;
		}
		return modelMapper.map(entity, VoteDto.class);
	}
	
	public Vote buildEntity(VoteDto dto) {
		if (dto == null) {
			return null;
		}
		return modelMapper.map(dto, Vote.class);
	}
	
	public Vote buildEntityFromRegistrationAndUser(VoteRequest dto, JwtUserResponse user) {
		if (dto == null || user == null) {
			log.error("Either dto or user is null. dto: {}, user: {}", dto.toString(), user.toString());
			return null;
		}
		Vote vote = modelMapper.map(dto, Vote.class);
		vote.setUserId(user.getUserId());
		return vote;
	}
}
