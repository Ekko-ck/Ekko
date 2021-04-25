package net.openobject.ekko.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtil {
	
	public static final ZoneId ZONE_ID_SEOUL = ZoneId.of("Asia/Seoul");

	public static Date getNowDate() {
		return new Date();
	}

	public static Date getDateAfterDays(Long days) {
		return Date.from(LocalDateTime.now(ZONE_ID_SEOUL).plusDays(days).toInstant(ZoneOffset.ofHours(9)));
	}
	
	public static Date getDateBeforeDays(Long days) {
		return Date.from(LocalDateTime.now(ZONE_ID_SEOUL).minusDays(days).toInstant(ZoneOffset.ofHours(9)));
	}
	
	public static LocalDateTime nowLocalDateTime() {
		return LocalDateTime.now(ZONE_ID_SEOUL);
	}
}
