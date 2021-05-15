package net.openobject.ekko.common.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class IndentityUtil {
	
	public static String randomUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", StringUtils.EMPTY);
	}

}
