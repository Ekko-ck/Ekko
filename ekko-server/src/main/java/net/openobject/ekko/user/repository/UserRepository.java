package net.openobject.ekko.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ekko.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserId(String userId);

	Integer deleteAllByUserSeqIn(List<Long> userSeqs);
	boolean existsByUserId(String userId);

	boolean existsByUserEmailAddr(String userEmailAddr);
}
