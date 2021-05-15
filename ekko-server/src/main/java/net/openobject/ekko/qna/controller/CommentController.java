package net.openobject.ekko.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.common.security.jwt.JwtUtils;
import net.openobject.ekko.qna.dto.CommentDto;
import net.openobject.ekko.qna.dto.CommentRegistrationReuqest;
import net.openobject.ekko.qna.service.CommentService;

@Slf4j
@RestController
@RequestMapping("/api/question")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/{questionId}/comment")
	public ApiResponse<CommentDto> registerToQuestion(
			@PathVariable(value = "questionId", required = true) String questionId,
			@RequestBody CommentRegistrationReuqest commentRegistrationReuqest) throws Exception {
		log.info("commentRegistrationReuqest: {}", commentRegistrationReuqest);
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		return ApiResponse.ok(commentService.registerCommentToQuestion(questionId, commentRegistrationReuqest, user));
	}
	
	@DeleteMapping("/{questionId}/comment/{commentId}")
	public ApiResponse<?> removeFromQuestion(
			@PathVariable(value = "questionId", required = true) String questionId,
			@PathVariable(value = "commentId", required = true) String commentId) throws Exception {
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		commentService.removeCommentFromQuestion(questionId, commentId, user);
		return ApiResponse.ok();
	}
	
	@PostMapping("/{questionId}/answer/{answerId}/comment")
	public ApiResponse<CommentDto> registerToAnswer(
			@PathVariable(value = "questionId", required = true) String questionId,
			@PathVariable(value = "answerId", required = true) String answerId,
			@RequestBody CommentRegistrationReuqest commentRegistrationReuqest) throws Exception {
		log.info("commentRegistrationReuqest: {}", commentRegistrationReuqest);
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		return ApiResponse.ok(commentService.registerCommentToAnswer(questionId, answerId, commentRegistrationReuqest, user));
	}
	
	@DeleteMapping("/{questionId}/answer/{answerId}/comment/{commentId}")
	public ApiResponse<?> removeFromAnswer(
			@PathVariable(value = "questionId", required = true) String questionId,
			@PathVariable(value = "answerId", required = true) String answerId,
			@PathVariable(value = "commentId", required = true) String commentId) throws Exception {
		JwtUserResponse user = jwtUtils.getLoginUserEntity();
		commentService.removeCommentFromAnswer(questionId, answerId, commentId, user);
		return ApiResponse.ok();
	}
	
}
