package net.openobject.ekko.qna.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.exception.BizException;
import net.openobject.ekko.common.response.ResultCode;
import net.openobject.ekko.qna.builder.CommentBuilder;
import net.openobject.ekko.qna.document.Answer;
import net.openobject.ekko.qna.document.Comment;
import net.openobject.ekko.qna.document.Question;
import net.openobject.ekko.qna.dto.CommentDto;
import net.openobject.ekko.qna.dto.CommentRegistrationReuqest;
import net.openobject.ekko.qna.helper.QnaValidationHelper;
import net.openobject.ekko.qna.repository.QuestionEsClient;

@Slf4j
@Service
public class CommentService {
	
	@Autowired
	private QuestionEsClient questionEsClient;
	@Autowired
	private CommentBuilder commentBuilder;
	@Autowired
	private QnaValidationHelper qnaValidationHelper;
	
	public CommentDto registerCommentToQuestion(String questionId, CommentRegistrationReuqest commentRegistrationReuqest, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		qnaValidationHelper.checkNullQuestion(question, questionId);
		
		Comment comment = commentBuilder.buildEntityFromRegistrationAndUser(commentRegistrationReuqest, user);
		question.addComment(comment);
		questionEsClient.update(question);
		return commentBuilder.buildDto(comment);
	}
	
	public void removeCommentFromQuestion(String questionId, String commentId, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		qnaValidationHelper.checkNullQuestion(question, questionId);
		
		List<Comment> comments = question.getComments();
		qnaValidationHelper.checkEmptyComments(comments, questionId, null);
		
		comments = excludeByIdAndCheckUser(comments, commentId, user);
		
		question.setComments(comments);
		questionEsClient.update(question);
	}
	
	public CommentDto registerCommentToAnswer(String questionId, String answerId, CommentRegistrationReuqest commentRegistrationReuqest, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		qnaValidationHelper.checkNullQuestion(question, questionId);
		
		List<Answer> answers = question.getAnswers();
		qnaValidationHelper.checkEmptyAnswers(answers, questionId);
		
		Comment comment = null;
		for (Answer answer : answers) {
			if (answer.getId().equals(answerId)) {
				comment = commentBuilder.buildEntityFromRegistrationAndUser(commentRegistrationReuqest, user);
				answer.addComment(comment);
			}
		}
		
		if (comment == null) {
			// if comment is null, not found answer by answerId.
			log.error("Not found answer. questionId: {}, answerId: {}", questionId, answerId);
			throw new BizException(ResultCode.NOT_FOUND);
		}
		
		question.setAnswers(answers);
		questionEsClient.update(question);
		return commentBuilder.buildDto(comment);
	}
	
	public void removeCommentFromAnswer(String questionId, String answerId, String commentId, JwtUserResponse user) throws Exception {
		Question question = questionEsClient.findById(questionId);
		qnaValidationHelper.checkNullQuestion(question, questionId);
		
		List<Answer> answers = question.getAnswers();
		qnaValidationHelper.checkEmptyAnswers(answers, questionId);
		
		List<Comment> comments = null;
		for (Answer answer : answers) {
			if (answer.getId().equals(answerId)) {
				comments = answer.getComments();
				qnaValidationHelper.checkEmptyComments(comments, questionId, answerId);
				
				comments = excludeByIdAndCheckUser(comments, commentId, user);
				answer.setComments(comments);
			}
		}
		
		if (comments == null) {
			// if comments is null, not found answer by answerId.
			log.error("Not found answer. questionId: {}, answerId: {}", questionId, answerId);
			throw new BizException(ResultCode.NOT_FOUND);
		}
		
		question.setAnswers(answers);
		questionEsClient.update(question);
	}
	
	private List<Comment> excludeByIdAndCheckUser(List<Comment> comments, String commentId, JwtUserResponse user) throws Exception {
		List<Comment> updateList = new ArrayList<>();
		for (Comment comment : comments) {
			if (comment.getId().equals(commentId)) {
				if (!comment.isSameUser(user.getUserId())) {
					throw new BizException(ResultCode.FORBIDDEN);
				}
				continue;
			}
			updateList.add(comment);
		}
		return updateList;
	}
	
}
