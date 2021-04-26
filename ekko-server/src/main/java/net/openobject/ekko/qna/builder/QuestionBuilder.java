package net.openobject.ekko.qna.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.openobject.ekko.qna.document.Question;
import net.openobject.ekko.qna.dto.QuestionDto;

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
	
}
