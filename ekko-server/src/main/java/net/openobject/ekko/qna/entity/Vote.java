package net.openobject.ekko.qna.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_VOTE")
public class Vote {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VOTE_SEQ")
	private Long voteSeq;

    @Column(name = "SOURCE_ID", nullable = false)
	private String sourceId;
	
    @Column(name = "USER_ID", nullable = false)
	private String userId;
	
    @Column(name = "VOTE_VALUE", nullable = false)
	private Long voteValue;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SOURCE_TYPE", nullable = false, columnDefinition = "enum('QUESTION', 'ANSWER')")
    private VoteSourceType sourceType;

}