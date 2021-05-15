package net.openobject.ekko.qna.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.openobject.ekko.common.auth.dto.JwtUserResponse;
import net.openobject.ekko.qna.document.Comment;
import net.openobject.ekko.qna.dto.CommentDto;
import net.openobject.ekko.qna.dto.CommentRegistrationReuqest;

@Slf4j
@Component
public class CommentBuilder {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CommentDto> buildDtoList(List<Comment> entityList) {
		return entityList.stream()
			.map(this::buildDto)
			.collect(Collectors.toList());
	}
	
	public CommentDto buildDto(Comment entity) {
		if (entity == null) {
			return null;
		}
		return modelMapper.map(entity, CommentDto.class);
	}
	
	public Comment buildEntity(CommentDto dto) {
		if (dto == null) {
			return null;
		}
		return modelMapper.map(dto, Comment.class);
	}
	
	public Comment buildEntityFromRegistrationAndUser(CommentRegistrationReuqest dto, JwtUserResponse user) {
		if (dto == null || user == null) {
			log.error("Either dto or user is null. dto: {}, user: {}", dto.toString(), user.toString());
			return null;
		}
		Comment comment = modelMapper.map(dto, Comment.class);
		comment.init(user);
		return comment;
	}
	
}
