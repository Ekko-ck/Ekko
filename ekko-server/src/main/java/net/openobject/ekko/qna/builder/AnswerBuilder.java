package net.openobject.ekko.qna.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.qna.document.Answer;
import net.openobject.ekko.qna.dto.AnswerDto;
import net.openobject.ekko.qna.dto.AnswerRegistrationReuqest;

@Slf4j
@Component
public class AnswerBuilder {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<AnswerDto> buildDtoList(List<Answer> entityList) {
		return entityList.stream()
			.map(this::buildDto)
			.collect(Collectors.toList());
	}
	
	public AnswerDto buildDto(Answer entity) {
		if (entity == null) {
			return null;
		}
		return modelMapper.map(entity, AnswerDto.class);
	}
	
	public Answer buildEntity(AnswerDto dto) {
		if (dto == null) {
			return null;
		}
		return modelMapper.map(dto, Answer.class);
	}
	
	public Answer buildEntityFromRegistrationAndUser(AnswerRegistrationReuqest dto, JwtUserResponse user) {
		if (dto == null || user == null) {
			log.error("Either dto or user is null. dto: {}, user: {}", dto.toString(), user.toString());
			return null;
		}
		Answer answer = modelMapper.map(dto, Answer.class);
		answer.init(user);
		return answer;
	}
	
}
