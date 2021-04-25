package net.openobject.ekko.common.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneralConstants {
	
	public static final Map<Object, Object> EMPTY_MAP = Collections.unmodifiableMap(new HashMap<>());
	public static final List<Object> EMPTY_LIST = Collections.unmodifiableList(new ArrayList<>());
	
	public static final String API_HEAD = "head";
	public static final String API_HEAD_RESULT_CODE = "resultCode";
	public static final String API_HEAD_RESULT_MESSAGE = "resultMessage";
	public static final String API_BODY = "body";

	public static final String RESULT_CODE_SUCCESS = "0000";
	public static final String RESULT_CODE_FAIL = "9999";
	public static final String RESULT_MESSAGE_SUCCESS = "SUCCESS";
	public static final String RESULT_MESSAGE_FAIL = "FAIL";
	
}
