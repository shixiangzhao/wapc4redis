package com.shixzh.spring.wpac4redis.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestIdUtil {

	private static final Logger logger = LoggerFactory.getLogger(RequestIdUtil.class);
	public static final String REQUEST_ID_KEY = "requestId";
	public static ThreadLocal<String> requestIdThreadLocal = new ThreadLocal<String>();

	public static String getRequestId(HttpServletRequest request) {
		String requestId = null;
		String parameterRequestId = request.getParameter(REQUEST_ID_KEY);
		String headerRequestId = request.getHeader(REQUEST_ID_KEY);

		if (parameterRequestId == null || headerRequestId == null) {
			logger.info("request parameter 和 header 都没有requestId入参");
			requestId = UUID.randomUUID().toString();
			logger.debug("Uese random UUID as requestId: " + requestId);
		} else {
			requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
		}
		requestIdThreadLocal.set(requestId);
		return requestId;
	}

}
