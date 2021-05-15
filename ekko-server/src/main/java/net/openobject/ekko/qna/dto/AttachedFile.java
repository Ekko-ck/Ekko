package net.openobject.ekko.qna.dto;

import lombok.Data;

@Data
public class AttachedFile {
	
	public static enum AttachedFileType {
		IMAGE,
		FILE
	}
	
	private AttachedFileType type;
	private String url;
}
