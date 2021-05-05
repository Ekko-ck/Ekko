package net.openobject.ekko.qna.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import net.openobject.ekko.qna.repository.QuestionEsClient;

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
	
	public QuestionDto registerQuestion(QuestionRegistrationReuqest questionRegistrationReuqest, JwtUserResponse user) {
		Question question = questionBuilder.buildEntityFromRegistrationAndUser(questionRegistrationReuqest, user);
		Question saved = questionEsClient.save(question);
		return questionBuilder.buildDto(saved);
	}
	
	public QuestionDto modifyQuestion(QuestionModificationReuqest questionModificationReuqest, JwtUserResponse user) throws Exception {
		String id = questionModificationReuqest.getId();
		if (StringUtils.isEmpty(id)) {
			throw new BizException(ResultCode.BAD_REQUEST, new Object[] { "id" });
		}
		
		Question question = questionEsClient.findById(questionModificationReuqest.getId());
		validate(question, user);
		
		question.setTitle(questionModificationReuqest.getTitle());
		question.setContents(questionModificationReuqest.getContents());
		question.setForModification(user.getUserId());
		
		Question saved = questionEsClient.update(question);
		return questionBuilder.buildDto(saved);
	}
	
	public void removeQuestion(String id, JwtUserResponse user) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new BizException(ResultCode.BAD_REQUEST);
		}
		
		Question question = questionEsClient.findById(id);
		validate(question, user);
		
		questionEsClient.delete(id);
	}
	
	private void validate(Question question, JwtUserResponse user) throws BizException {
		if (question == null) {
			throw new BizException(ResultCode.NOT_FOUND);
		}
		if (!question.getUserId().equals(user.getUserId())) {
			throw new BizException(ResultCode.FORBIDDEN);
		}
	}
	
}
