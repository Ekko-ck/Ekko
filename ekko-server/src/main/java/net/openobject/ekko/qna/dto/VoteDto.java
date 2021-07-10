package net.openobject.ekko.qna.dto;

import lombok.Data;

@Data
public class VoteDto {
	private Long id;
	private String sourceId;
	private String userId;
	private String voteValue;
	private String sourceType;
}
