package net.openobject.ekko.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ekko.user.entity.UserERole;
import net.openobject.ekko.user.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	/**
	* 이름으로 사용자정보 조회 
	*/
	Optional<UserRole> findByName(UserERole name);
}
