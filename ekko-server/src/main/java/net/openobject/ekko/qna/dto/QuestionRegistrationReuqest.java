package net.openobject.ekko.qna.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionRegistrationReuqest {
	
	private String title;
	private String contents;
	private List<String> tags;
	
}
