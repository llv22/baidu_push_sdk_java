package com.baidu.cloud.channel.transform;

import java.util.Map;

import com.baidu.cloud.channel.exception.ChannelClientException;
import com.baidu.cloud.channel.exception.ChannelServerException;
import com.baidu.cloud.channel.model.ChannelResponse;
import com.baidu.cloud.core.json.JSONParser;
import com.baidu.cloud.core.model.ErrorResponse;
import com.baidu.cloud.core.utility.MapObjectUtility;

public class ChannelRestResponseJsonUnmapper {

    public <X extends ChannelResponse> X unmarshall(int httpStatusCode,
            String jsonResponse, X resp) throws ChannelClientException,
            ChannelServerException {

        if (jsonResponse == null) {
            return resp;
        }

        if (httpStatusCode == 200) {
            try {
                JSONParser parser = new JSONParser();
                Map<?, ?> map = parser.parser(jsonResponse);
                if (resp != null) {
                    MapObjectUtility.convertMap2ObjectWithJson(resp, map);
                }
            } catch (Throwable e) {
                throw new ChannelClientException("parse json failed : "
                        + jsonResponse);
            }
            return resp;
        } else {
            Map<?, ?> map = null;
            try {
                JSONParser parser = new JSONParser();
                map = parser.parser(jsonResponse);
            } catch (Throwable e) {
                throw new ChannelClientException("parse json failed : "
                        + jsonResponse);
            }
            ErrorResponse response = new ErrorResponse();
            MapObjectUtility.convertMap2ObjectWithJson(response, map);
            if (response.validate() == true) {
                throw new ChannelServerException(response.getRequestId(),
                        response.getErrorCode(), response.getErrorMsg());
            } else {
                response.buildFromMap(map);
                if (response.validate() == true) {
                    throw new ChannelServerException(response.getRequestId(),
                            response.getErrorCode(), response.getErrorMsg());
                }
            }
            throw new ChannelClientException("unknown error msg for json : "
                    + jsonResponse);
        }
    }

    public void unmarshall(int httpStatusCode, String jsonResponse)
            throws ChannelClientException, ChannelServerException {

        if (jsonResponse == null) {
            return;
        }

        if (httpStatusCode == 200) {
            try {
                JSONParser parser = new JSONParser();
                Map<?, ?> map = parser.parser(jsonResponse);
                if (map.containsKey("request_id")) {
                    return;
                }
                /*
                 * if (resp != null) {
                 * MapObjectUtility.convertMap2ObjectWithJson(resp, map); }
                 */
            } catch (Throwable e) {
                throw new ChannelClientException("parse json failed : "
                        + jsonResponse);
            }
            return;
        } else {
            Map<?, ?> map = null;
            try {
                JSONParser parser = new JSONParser();
                map = parser.parser(jsonResponse);
            } catch (Throwable e) {
                throw new ChannelClientException("parse json failed : "
                        + jsonResponse);
            }
            ErrorResponse response = new ErrorResponse();
            MapObjectUtility.convertMap2ObjectWithJson(response, map);
            if (response.validate() == true) {
                throw new ChannelServerException(response.getRequestId(),
                        response.getErrorCode(), response.getErrorMsg());
            } else {
                response.buildFromMap(map);
                if (response.validate() == true) {
                    throw new ChannelServerException(response.getRequestId(),
                            response.getErrorCode(), response.getErrorMsg());
                }
            }
            throw new ChannelClientException("unknown error msg for json : "
                    + jsonResponse);
        }
    }

}
