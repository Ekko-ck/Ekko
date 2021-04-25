package net.openobject.ekko.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ekko.user.entity.UserERole;
import net.openobject.ekko.user.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	
	/**
	 * findByName <br/>
	 * 권한명으로 사용자권한정보 조회
	 * 
	 * @author : SeHoon
	 * @version : 1.0
	 */
	UserRole findByName(UserERole name);
}
