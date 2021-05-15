package net.openobject.ekko.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.exception.BizException;
import net.openobject.ekko.common.response.ResultCode;
import net.openobject.ekko.qna.builder.QuestionBuilder;
import net.openobject.ekko.qna.document.Question;
import net.openobject.ekko.qna.dto.QuestionDto;
import net.openobject.ekko.qna.dto.QuestionModificationReuqest;
import net.openobject.ekko.qna.dto.QuestionRegistrationReuqest;
import net.openobject.ekko.qna.helper.QnaValidationHelper;
import net.openobject.ekko.qna.repository.QuestionEsClient;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionEsClient questionEsClient;
	@Autowired
	private QuestionBuilder questionBuilder;
	@Autowired
	private QnaValidationHelper qnaValidationHelper;
	
	public List<QuestionDto> searchQuestion(Pageable pageable, String query) {
		List<Question> questionList = questionEsClient.search(pageable, query);
		return questionBuilder.buildDtoList(questionList);
	}
	
	public QuestionDto registerQuestion(QuestionRegistrationReuqest questionRegistrationReuqest, JwtUserResponse user) {
		Question question = questionBuilder.buildEntityFromRegistrationAndUser(questionRegistrationReuqest, user);
		Question saved = questionEsClient.save(question);
		return questionBuilder.buildDto(saved);
	}
	
	public QuestionDto modifyQuestion(String questionId, QuestionModificationReuqest questionModificationReuqest, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		validate(question, questionId, user);
		
		question.setTitle(questionModificationReuqest.getTitle());
		question.setContents(questionModificationReuqest.getContents());
		question.setForModification(user.getUserId());
		
		Question saved = questionEsClient.update(question);
		return questionBuilder.buildDto(saved);
	}
	
	public void removeQuestion(String questionId, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		validate(question, questionId, user);
		questionEsClient.delete(questionId);
	}
	
	private void validate(Question question, String questionId, JwtUserResponse user) throws BizException {
		qnaValidationHelper.checkNullQuestion(question, questionId);
		if (!question.isSameUser(user.getUserId())) {
			throw new BizException(ResultCode.FORBIDDEN);
		}
	}
	
}
