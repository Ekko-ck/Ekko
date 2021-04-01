package net.openobject.ekko.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ekko.user.entity.UserRoles;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
}
