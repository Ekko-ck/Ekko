package net.openobject.ekko.qna.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.qna.document.Question;
import net.openobject.ekko.qna.dto.QuestionDto;
import net.openobject.ekko.qna.dto.QuestionRegistrationReuqest;

@Slf4j
@Component
public class QuestionBuilder {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<QuestionDto> buildDtoList(List<Question> entityList) {
		return entityList.stream()
			.map(this::buildDto)
			.collect(Collectors.toList());
	}
	
	public QuestionDto buildDto(Question entity) {
		if (entity == null) {
			return null;
		}
		return modelMapper.map(entity, QuestionDto.class);
	}
	
	public Question buildEntity(QuestionDto dto) {
		if (dto == null) {
			return null;
		}
		return modelMapper.map(dto, Question.class);
	}
	
	public Question buildEntityFromRegistrationAndUser(QuestionRegistrationReuqest dto, JwtUserResponse user) {
		if (dto == null || user == null) {
			log.error("Either dto or user is null. dto: {}, user: {}", dto.toString(), user.toString());
			return null;
		}
		Question question = modelMapper.map(dto, Question.class);
		question.init(user);
		return question;
	}
	
}
