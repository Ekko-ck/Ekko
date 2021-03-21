package net.openobject.ekko.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.openobject.ekko.sample.entity.SampleUser;

@Repository
public interface SampleUserRepository extends JpaRepository<SampleUser, Long> {

}
