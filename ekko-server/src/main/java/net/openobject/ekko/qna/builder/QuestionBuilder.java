package net.openobject.ekko.qna.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.qna.document.Question;
import net.openobject.ekko.qna.dto.AnswerDto;
import net.openobject.ekko.qna.dto.CommentDto;
import net.openobject.ekko.qna.dto.QuestionDto;
import net.openobject.ekko.qna.dto.QuestionRegistrationReuqest;

@Slf4j
@Component
public class QuestionBuilder {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<QuestionDto> buildDtoList(List<Question> entityList, JwtUserResponse user) {
		String loginUserId = user.getUserId();
		return entityList.stream()
			.map(entity -> {
				QuestionDto questionDto = buildDto(entity);
				questionDto.setIsMine(loginUserId);
				
				List<AnswerDto> answers = questionDto.getAnswers();
				setIsMineToAnswerAndComments(answers, loginUserId);
				
				List<CommentDto> comments = questionDto.getComments();
				setIsMineToComments(comments, loginUserId);
				
				return questionDto;
			})
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
	
	private void setIsMineToAnswerAndComments(List<AnswerDto> list, String loginUserId) {
		if (list == null) {
			return;
		}
		list.forEach(answerDto -> {
			answerDto.setIsMine(loginUserId);
			List<CommentDto> comments = answerDto.getComments();
			setIsMineToComments(comments, loginUserId);
		});
	}
	
	private void setIsMineToComments(List<CommentDto> list, String loginUserId) {
		if (list == null) {
			return;
		}
		list.forEach(commentDto -> {
			commentDto.setIsMine(loginUserId);
		});
	}
	
}
