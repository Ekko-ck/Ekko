package net.openobject.ekko.qna.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.response.ApiResponse;
import net.openobject.ekko.qna.dto.QuestionDto;
import net.openobject.ekko.qna.service.QuestionService;

@Slf4j
@RestController
@RequestMapping("/api/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/search")
	public ApiResponse<List<QuestionDto>> search(
			Pageable pageable,
			@RequestParam(defaultValue = StringUtils.EMPTY, required = false) String query) {
		log.info("pageable: {}", pageable.toString());
		log.info("query: {}", query);
		return ApiResponse.ok(questionService.searchQuestion(pageable, query));
	}
	
}
