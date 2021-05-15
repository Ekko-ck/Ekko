package net.openobject.ekko.qna.helper;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.exception.BizException;
import net.openobject.ekko.common.response.ResultCode;
import net.openobject.ekko.qna.document.Answer;
import net.openobject.ekko.qna.document.Comment;
import net.openobject.ekko.qna.document.Question;

@Slf4j
@Component
public class QnaValidationHelper {
	
	public void checkNullQuestion(Question question, String questionId) throws BizException  {
		if (question == null) {
			log.error("Not found question. questionId: {}", questionId);
			throw new BizException(ResultCode.NOT_FOUND);
		}
	}
	
	public void checkEmptyAnswers(List<Answer> answers, String questionId) throws BizException  {
		if (answers == null || answers.isEmpty()) {
			log.error("Not found answers. questionId: {}", questionId);
			throw new BizException(ResultCode.NOT_FOUND);
		}
	}
	
	public void checkNullAnswer(Answer answer, String questionId, String answerId) throws BizException  {
		if (answer == null) {
			log.error("Not found answer. questionId: {}, answerId: {}", questionId, answerId);
			throw new BizException(ResultCode.NOT_FOUND);
		}
	}
	
	public void checkEmptyComments(List<Comment> comments, String questionId, String answerId) throws BizException  {
		if (comments == null || comments.isEmpty()) {
			log.error("Not found comments. questionId: {}, answerId: {}", questionId, answerId);
			throw new BizException(ResultCode.NOT_FOUND);
		}
	}
	
}
