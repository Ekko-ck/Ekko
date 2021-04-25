package net.openobject.ekko.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.openobject.ekko.common.entity.BaseEntity;
import net.openobject.ekko.sample.dto.SampleUserRequest;

@Entity
@Table(name = "sample_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SampleUser extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "name")
	private String userName; // leaf -> leaf 님
	
	public SampleUser update(SampleUserRequest formDto) {
		this.userName = formDto.getUserName();
		return this;
	}
	
}
