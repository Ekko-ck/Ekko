package net.openobject.ekko.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.common.security.jwt.JwtUtils;
import net.openobject.ekko.qna.dto.AnswerDto;
import net.openobject.ekko.qna.dto.AnswerModificationReuqest;
import net.openobject.ekko.qna.dto.AnswerRegistrationReuqest;
import net.openobject.ekko.qna.service.AnswerService;

@Api(tags = "QnA > 답변")
@Slf4j
@RestController
@RequestMapping("/api/question")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	@Autowired
	private JwtUtils jwtUtils;
	
	@ApiOperation(value = "QnA > 답변 등록")
	@PostMapping("/{questionId}/answer")
	public ApiResponse<AnswerDto> register(
			@PathVariable(value = "questionId", required = true) String questionId,
			@RequestBody AnswerRegistrationReuqest answerRegistrationReuqest) throws Exception {
		log.info("answerRegistrationReuqest: {}", answerRegistrationReuqest);
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		return ApiResponse.ok(answerService.registerAnswer(questionId, answerRegistrationReuqest, user));
	}
	
	@ApiOperation(value = "QnA > 답변 수정")
	@PutMapping("/{questionId}/answer/{answerId}")
	public ApiResponse<AnswerDto> modify(
			@PathVariable(value = "questionId", required = true) String questionId,
			@PathVariable(value = "answerId", required = true) String answerId,
			@RequestBody AnswerModificationReuqest answerModificationRequest) throws Exception {
		log.info("answerModificationRequest: {}", answerModificationRequest);
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		return ApiResponse.ok(answerService.modifyAnswer(questionId, answerId, answerModificationRequest, user));
	}
	
	@ApiOperation(value = "QnA > 답변 삭제")
	@DeleteMapping("/{questionId}/answer/{answerId}")
	public ApiResponse<?> remove(
			@PathVariable(value = "questionId", required = true) String questionId,
			@PathVariable(value = "answerId", required = true) String answerId) throws Exception {
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		answerService.removeAnswer(questionId, answerId, user);
		return ApiResponse.ok();
	}
	
}
