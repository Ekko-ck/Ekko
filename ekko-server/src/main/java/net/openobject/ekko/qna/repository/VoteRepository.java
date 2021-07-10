package net.openobject.ekko.qna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ekko.qna.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
	List<Vote> findBySourceId(String sourceId);
	Vote findBySourceIdAndUserId(String sourceId, String userId);
}
