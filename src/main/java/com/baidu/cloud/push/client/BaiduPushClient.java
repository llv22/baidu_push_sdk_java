package com.baidu.cloud.push.client;

import java.util.Map;

import com.baidu.cloud.core.callback.CloudLogHttpCallBack;
import com.baidu.cloud.core.exception.CloudHttpClientException;
import com.baidu.cloud.core.httpclient.CloudHttpClient;
import com.baidu.cloud.core.log.CloudLogHandler;
import com.baidu.cloud.core.model.HttpRestResponse;
import com.baidu.cloud.push.auth.PushKeyPair;
import com.baidu.cloud.push.auth.signature.PushSignatureDigest;
import com.baidu.cloud.push.constants.BaiduPushConstants;
import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.AddDevicesToTagRequest;
import com.baidu.cloud.push.model.AddDevicesToTagResponse;
import com.baidu.cloud.push.model.CreateTagRequest;
import com.baidu.cloud.push.model.CreateTagResponse;
import com.baidu.cloud.push.model.DeleteDevicesFromTagRequest;
import com.baidu.cloud.push.model.DeleteDevicesFromTagResponse;
import com.baidu.cloud.push.model.DeleteTagRequest;
import com.baidu.cloud.push.model.DeleteTagResponse;
import com.baidu.cloud.push.model.PushBatchUniMsgRequest;
import com.baidu.cloud.push.model.PushBatchUniMsgResponse;
import com.baidu.cloud.push.model.PushMsgToAllRequest;
import com.baidu.cloud.push.model.PushMsgToAllResponse;
import com.baidu.cloud.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.cloud.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.cloud.push.model.PushMsgToSmartTagRequest;
import com.baidu.cloud.push.model.PushMsgToSmartTagResponse;
import com.baidu.cloud.push.model.PushMsgToTagRequest;
import com.baidu.cloud.push.model.PushMsgToTagResponse;
import com.baidu.cloud.push.model.PushRequest;
import com.baidu.cloud.push.model.QueryDeviceNumInTagRequest;
import com.baidu.cloud.push.model.QueryDeviceNumInTagResponse;
import com.baidu.cloud.push.model.QueryMsgStatusRequest;
import com.baidu.cloud.push.model.QueryMsgStatusResponse;
import com.baidu.cloud.push.model.QueryStatisticDeviceRequest;
import com.baidu.cloud.push.model.QueryStatisticDeviceResponse;
import com.baidu.cloud.push.model.QueryStatisticMsgRequest;
import com.baidu.cloud.push.model.QueryStatisticMsgResponse;
import com.baidu.cloud.push.model.QueryStatisticTopicRequest;
import com.baidu.cloud.push.model.QueryStatisticTopicResponse;
import com.baidu.cloud.push.model.QueryTagsRequest;
import com.baidu.cloud.push.model.QueryTagsResponse;
import com.baidu.cloud.push.model.QueryTimerListRequest;
import com.baidu.cloud.push.model.QueryTimerListResponse;
import com.baidu.cloud.push.model.QueryTimerRecordsRequest;
import com.baidu.cloud.push.model.QueryTimerRecordsResponse;
import com.baidu.cloud.push.model.QueryTopicListRequest;
import com.baidu.cloud.push.model.QueryTopicListResponse;
import com.baidu.cloud.push.model.QueryTopicRecordsRequest;
import com.baidu.cloud.push.model.QueryTopicRecordsResponse;
import com.baidu.cloud.push.transform.PushRestRequestChecker;
import com.baidu.cloud.push.transform.PushRestRequestMapper;
import com.baidu.cloud.push.transform.PushRestResponseJsonUnmapper;

public class BaiduPushClient implements BaiduPush {

	private String apiKey = null;

	private String secretKey = null;

	private String host = null;

	private CloudLogHttpCallBack logHttpCallback = new CloudLogHttpCallBack();

	private PushRestResponseJsonUnmapper responseJsonUnmapper = new PushRestResponseJsonUnmapper();

	public BaiduPushClient(PushKeyPair pair) {
		this(pair, BaiduPushConstants.CHANNEL_REST_URL);
	}

	public BaiduPushClient(PushKeyPair pair, String host) {
		this.apiKey = pair.getApiKey();
		this.secretKey = pair.getSecretKey();
		this.host = host;
	}

    @Override
	public PushMsgToSingleDeviceResponse pushMsgToSingleDevice (
			PushMsgToSingleDeviceRequest request) 
	        throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH, 
				"single_device", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
				resp.getJsonResponse(), new PushMsgToSingleDeviceResponse());
	}

    @Override
	public PushMsgToAllResponse pushMsgToAll (
			PushMsgToAllRequest request) 
	        throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH, 
				"all", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
				resp.getJsonResponse(), new PushMsgToAllResponse());
	}
    
    @Override
    public PushMsgToTagResponse pushMsgToTag (PushMsgToTagRequest request) 
    		throws PushClientException, PushServerException {
    	HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH,
    			"tags", request);
    	return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
    			resp.getJsonResponse(), new PushMsgToTagResponse());
    }
    
    @Override
    public PushMsgToSmartTagResponse pushMsgToSmartTag (PushMsgToSmartTagRequest request) 
    		throws PushClientException, PushServerException {
    	HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH,
    			"tags", request);
    	return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
    			resp.getJsonResponse(), new PushMsgToSmartTagResponse());
    }
    
	@Override
	public PushBatchUniMsgResponse pushBatchUniMsg (PushBatchUniMsgRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSPUSH,
				"batch_device", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new PushBatchUniMsgResponse());
	}
	
	@Override
	public QueryMsgStatusResponse queryMsgStatus (QueryMsgStatusRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"query_msg_status", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryMsgStatusResponse());
	}
	
	@Override
	public QueryTimerRecordsResponse queryTimerRecords (QueryTimerRecordsRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"query_timer_records", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTimerRecordsResponse());
	}
	
	@Override
	public QueryTopicRecordsResponse queryTopicRecords (QueryTopicRecordsRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"query_topic_records", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTopicRecordsResponse());
	}
	
	@Override
	public QueryTimerListResponse queryTimerList (QueryTimerListRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTIMER,
				"query_list", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTimerListResponse());
	}
	
	@Override
	public QueryTopicListResponse queryTopicList (QueryTopicListRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTOPIC,
				"query_list", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTopicListResponse());
	}
	
	@Override
	public QueryTagsResponse queryTags (QueryTagsRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSAPP,
				"query_tags", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryTagsResponse());
	}
	
	@Override
	public CreateTagResponse createTag (CreateTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSAPP,
				"create_tag", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new CreateTagResponse());
	}
	
	@Override
	public DeleteTagResponse deleteTag (DeleteTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSAPP,
				"del_tag", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new DeleteTagResponse());
	}
	
	@Override
	public AddDevicesToTagResponse addDevicesToTag (AddDevicesToTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTAG,
				"add_devices", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new AddDevicesToTagResponse());
	}
	
	@Override
	public DeleteDevicesFromTagResponse deleteDevicesFromTag (DeleteDevicesFromTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTAG,
				"del_devices", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new DeleteDevicesFromTagResponse());
	}
	
	@Override
	public QueryDeviceNumInTagResponse queryDeviceNumInTag (QueryDeviceNumInTagRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSTAG,
				"device_num", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryDeviceNumInTagResponse());
	}
	

	@Override
	public QueryStatisticMsgResponse queryStatisticMsg (
			QueryStatisticMsgRequest request) throws PushClientException,
			PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"statistic_msg", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryStatisticMsgResponse());
	}

	@Override
	public QueryStatisticTopicResponse queryStatisticTopic (
			QueryStatisticTopicRequest request) throws PushClientException,
			PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"statistic_topic", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryStatisticTopicResponse());
	}

	@Override
	public QueryStatisticDeviceResponse queryStatisticDevice (
			QueryStatisticDeviceRequest request)
			throws PushClientException, PushServerException {
		HttpRestResponse resp = process(BaiduPushConstants.HTTPCLASSREPORT,
				"statistic_device", request);
		return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(), 
				resp.getJsonResponse(), new QueryStatisticDeviceResponse());
	}

	public void setChannelLogHandler(CloudLogHandler logHandler) {
		logHttpCallback.setHandler(logHandler);
	}

	// -----------------------------------------------------------

	private HttpRestResponse process(String classType, String method, PushRequest request)
			throws PushClientException, PushServerException {

		PushRestRequestChecker checker = new PushRestRequestChecker();
		checker.validate(request);

		PushRestRequestMapper mapper = new PushRestRequestMapper();
		Map<String, String> params = mapper.marshall(request);
		params.put("apikey", apiKey);

		String surl = obtainIntegrityUrl(classType, method);

		PushSignatureDigest digest = new PushSignatureDigest();
		String sign = digest.digest(BaiduPushConstants.HTTPMETHOD, surl, secretKey, params);
		params.put("sign", sign);
		
		CloudHttpClient client = new CloudHttpClient();
		client.addHttpCallback(logHttpCallback);
		
		try {
			return client.doExecutePostRequestResponse(surl, params);
		} catch(CloudHttpClientException e) {
			throw new PushClientException(e.getMessage());
		}
	}

	private String obtainIntegrityUrl(String classType, String method) {
		String resurl = host;
		if (host.startsWith("http://") || host.startsWith("https://")) {
		} else {
			resurl = "http://" + host;
		}
		if (resurl.endsWith("/")) {
			resurl = resurl + "rest/3.0/";
		} else {
			resurl = resurl + "/rest/3.0/";
		}
		resurl = resurl + classType + "/" + method;
		return resurl;
	}

}
