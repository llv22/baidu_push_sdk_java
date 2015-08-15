package com.baidu.cloud.push.transform;

import java.util.Map;

import com.baidu.cloud.core.json.JSONParser;
import com.baidu.cloud.core.model.ErrorResponse;
import com.baidu.cloud.core.utility.MapObjectUtility;
import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.PushResponse;

public class PushRestResponseJsonUnmapper {

	@SuppressWarnings("rawtypes")
	public <X extends PushResponse> X unmarshall(int httpStatusCode,
			String jsonResponse, X resp) throws PushClientException,
			PushServerException {

		if (jsonResponse == null) {
			return resp;
		}

		jsonResponse = transformHttpHead(jsonResponse);
		
		if (httpStatusCode == 200) {
			try {
				JSONParser parser = new JSONParser();
				Map map = parser.parser(jsonResponse);
				if (resp != null) {
					MapObjectUtility.convertMap2ObjectWithJson(resp, map);
				}
			} catch (Throwable e) {
				throw new PushClientException("parse json failed : "
						+ jsonResponse);
			}
			return resp;
		} else {
			Map map = null;
			try {
				JSONParser parser = new JSONParser();
				map = parser.parser(jsonResponse);
			} catch (Throwable e) {
				throw new PushClientException("parse json failed : "
						+ jsonResponse);
			}
			ErrorResponse response = new ErrorResponse();
			MapObjectUtility.convertMap2ObjectWithJson(response, map);
			if (response.validate() == true) {
				throw new PushServerException(response.getRequestId(),
						response.getErrorCode(), response.getErrorMsg());
			} else {
				response.buildFromMap(map);
				if (response.validate() == true) {
					throw new PushServerException(response.getRequestId(),
							response.getErrorCode(), response.getErrorMsg());
				}
			}
			throw new PushClientException("unknown error msg for json : "
					+ jsonResponse);
		}
	}

	@SuppressWarnings("rawtypes")
	public void unmarshall(int httpStatusCode,
			String jsonResponse) throws PushClientException,
			PushServerException {

		if (jsonResponse == null) {
			return;
		}

		if (httpStatusCode == 200) {
			try {
				JSONParser parser = new JSONParser();
				Map map = parser.parser(jsonResponse);
				if ( map.containsKey("request_id") ) {
					return;
				}
				/*
				if (resp != null) {
					MapObjectUtility.convertMap2ObjectWithJson(resp, map);
				}
				*/
			} catch (Throwable e) {
				throw new PushClientException("parse json failed : "
						+ jsonResponse);
			}
			return;
		} else {
			Map map = null;
			try {
				JSONParser parser = new JSONParser();
				map = parser.parser(jsonResponse);
			} catch (Throwable e) {
				throw new PushClientException("parse json failed : "
						+ jsonResponse);
			}
			ErrorResponse response = new ErrorResponse();
			MapObjectUtility.convertMap2ObjectWithJson(response, map);
			if (response.validate() == true) {
				throw new PushServerException(response.getRequestId(),
						response.getErrorCode(), response.getErrorMsg());
			} else {
				response.buildFromMap(map);
				if (response.validate() == true) {
					throw new PushServerException(response.getRequestId(),
							response.getErrorCode(), response.getErrorMsg());
				}
			}
			throw new PushClientException("unknown error msg for json : "
					+ jsonResponse);
		}
	}
	
	// 转化"http:\\\/\\\/" => "http://"
	private String transformHttpHead (String strContainsHttp) {
		String ret = strContainsHttp;
		
		char[] httpToken = {'h','t','t','p',':','\\','\\','\\','/','\\','\\','\\','/'};
		StringBuilder strHttpToken = new StringBuilder();
		for (char charToken : httpToken) strHttpToken.append(charToken);
		
		if (strContainsHttp.contains(strHttpToken)) {
			char[] targetHttpToken = {'h','t','t','p',':','/','/'};
			StringBuilder targetStrHttpToken = new StringBuilder();
			for (char targetCharToken : targetHttpToken) targetStrHttpToken.append(targetCharToken);
			
			ret = strContainsHttp.replace(strHttpToken, targetStrHttpToken);
		}
		
		return ret;
	}

}
