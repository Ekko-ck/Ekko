package net.openobject.ekko.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.qna.builder.QuestionBuilder;
import net.openobject.ekko.qna.document.Question;
import net.openobject.ekko.qna.dto.QuestionDto;
import net.openobject.ekko.qna.repository.QuestionEsClient;

@Slf4j
@Service
public class QuestionService {
	
	@Autowired
	private QuestionEsClient questionEsClient;
	
	@Autowired
	private QuestionBuilder questionBuilder;
	
	public List<QuestionDto> searchQuestion(Pageable pageable, String query) {
		List<Question> questionList = questionEsClient.search(pageable, query);
		return questionBuilder.buildDtoList(questionList);
	}
	
	public Question getQuestion(String id) {
		
		return null;
	}
	
	public Question registerQuestion(String id) {
		
		return null;
	}
	
	public Question modifyQuestion(String id) {
		
		return null;
	}
	
	public boolean removeQuestion(String id) {
		
		return true;
	}
	
}
