package net.openobject.ekko.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.openobject.ekko.sample.builder.SampleUserBuilder;
import net.openobject.ekko.sample.dto.SampleUserRequest;
import net.openobject.ekko.sample.dto.SampleUserResponse;
import net.openobject.ekko.sample.entity.SampleUser;
import net.openobject.ekko.sample.repository.SampleUserRepository;

@Service
public class SampleUserService {
	
	@Autowired
	private SampleUserRepository sampleUserRepository;
	@Autowired
	private SampleUserBuilder sampleUserBuilder;
	
	@Transactional(readOnly = true)
	public List<SampleUserResponse> getUserList() {
		List<SampleUser> list = sampleUserRepository.findAll();
		return sampleUserBuilder.buildDtoList(list);
	}
	
	@Transactional(readOnly = false)
	public SampleUserResponse getUser(Long id) {
		SampleUser entity = sampleUserRepository.findById(id).orElse(null);
		return sampleUserBuilder.buildDto(entity);
	}
	
	@Transactional(readOnly = false)
	public SampleUserResponse registerUser(SampleUserRequest formDto) {
		SampleUser entity = sampleUserBuilder.buildEntityForRegister(formDto);
		entity = sampleUserRepository.save(entity);
		return sampleUserBuilder.buildDto(entity);
	}
	
	@Transactional(readOnly = false)
	public SampleUserResponse modifyUser(SampleUserRequest formDto) {
		SampleUser entity = sampleUserRepository.findById(formDto.getId()).orElse(null);
		entity = entity.update(formDto);
		return sampleUserBuilder.buildDto(entity);
	}
	
	@Transactional(readOnly = false)
	public void removeUser(Long id) {
		sampleUserRepository.deleteById(id);
	}
	
}
