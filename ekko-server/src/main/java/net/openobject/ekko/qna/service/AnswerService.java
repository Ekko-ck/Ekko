package net.openobject.ekko.qna.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.exception.BizException;
import net.openobject.ekko.common.response.ResultCode;
import net.openobject.ekko.qna.builder.AnswerBuilder;
import net.openobject.ekko.qna.document.Answer;
import net.openobject.ekko.qna.document.Question;
import net.openobject.ekko.qna.dto.AnswerDto;
import net.openobject.ekko.qna.dto.AnswerModificationReuqest;
import net.openobject.ekko.qna.dto.AnswerRegistrationReuqest;
import net.openobject.ekko.qna.helper.QnaValidationHelper;
import net.openobject.ekko.qna.repository.QuestionEsClient;

@Slf4j
@Service
public class AnswerService {
	
	@Autowired
	private QuestionEsClient questionEsClient;
	@Autowired
	private AnswerBuilder answerBuilder;
	@Autowired
	private QnaValidationHelper qnaValidationHelper;
	
	public AnswerDto registerAnswer(String questionId, AnswerRegistrationReuqest answerRegistrationReuqest, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		qnaValidationHelper.checkNullQuestion(question, questionId);
		
		Answer answer = answerBuilder.buildEntityFromRegistrationAndUser(answerRegistrationReuqest, user);
		question.addAnswer(answer);
		questionEsClient.update(question);
		return answerBuilder.buildDto(answer);
	}
	
	public AnswerDto modifyAnswer(String questionId, String answerId, AnswerModificationReuqest answerModificationReuqest, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		qnaValidationHelper.checkNullQuestion(question, questionId);
		
		List<Answer> answers = question.getAnswers();
		qnaValidationHelper.checkEmptyAnswers(answers, questionId);
		
		String userId = user.getUserId();
		Answer found = null;
		
		for (Answer answer : answers) {
			if (answer.getId().equals(answerId)) {
				if (!answer.isSameUser(userId)) {
					log.error("Not same user. answerUserId: {}, requestedUserId: {}", answer.getUserId(), userId);
					throw new BizException(ResultCode.FORBIDDEN);
				}
				
				answer.setContents(answerModificationReuqest.getContents());
				answer.setForModification(userId);
				found = answer;
				break;
			}
		}
		qnaValidationHelper.checkNullAnswer(found, questionId, answerId);
		
		question.setAnswers(answers);
		questionEsClient.update(question);
		return answerBuilder.buildDto(found);
	}
	
	public void removeAnswer(String questionId, String answerId, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		qnaValidationHelper.checkNullQuestion(question, questionId);
		
		List<Answer> answers = question.getAnswers();
		qnaValidationHelper.checkEmptyAnswers(answers, questionId);
		
		List<Answer> updateList = new ArrayList<>();
		
		for (Answer answer : answers) {
			if (answer.getId().equals(answerId)) {
				if (!answer.isSameUser(user.getUserId())) {
					log.error("Not same user. answerUserId: {}, requestedUserId: {}", answer.getUserId(), user.getUserId());
					throw new BizException(ResultCode.FORBIDDEN);
				}
				continue;
			}
			updateList.add(answer);
		}
		
		question.setAnswers(updateList);
		questionEsClient.update(question);
	}
	
}
