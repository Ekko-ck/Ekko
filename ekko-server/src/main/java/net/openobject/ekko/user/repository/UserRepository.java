package net.openobject.ekko.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ekko.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * findByUserId <br/>
	 * 사용자아이디로 사용자 정보 조회
	 * 
	 * @author : SeHoon
	 * @version : 1.0
	 */
	Optional<User> findByUserId(String userId);

	/**
	 * existsByUserId <br/>
	 * 사용자아이디로 사용자 존재여부 조회
	 * 
	 * @author : SeHoon
	 * @version : 1.0
	 */
	boolean existsByUserId(String userId);

}
