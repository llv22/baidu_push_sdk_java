package com.baidu.cloud.push.client;

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

public interface BaiduPush {

	public PushMsgToSingleDeviceResponse pushMsgToSingleDevice (
			PushMsgToSingleDeviceRequest request) throws PushClientException,
		    PushServerException;
	
	public PushMsgToAllResponse pushMsgToAll (
			PushMsgToAllRequest request) throws PushClientException,
		    PushServerException;
	
	public PushMsgToTagResponse pushMsgToTag (
			PushMsgToTagRequest request) throws PushClientException, 
	PushServerException;
	
	public PushMsgToSmartTagResponse pushMsgToSmartTag (
			PushMsgToSmartTagRequest request) throws PushClientException, 
	PushServerException;
	
	public PushBatchUniMsgResponse pushBatchUniMsg (
			PushBatchUniMsgRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryMsgStatusResponse queryMsgStatus (
			QueryMsgStatusRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTimerRecordsResponse queryTimerRecords (
			QueryTimerRecordsRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTopicRecordsResponse queryTopicRecords (
			QueryTopicRecordsRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTimerListResponse queryTimerList (
			QueryTimerListRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTopicListResponse queryTopicList (
			QueryTopicListRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryTagsResponse queryTags (
			QueryTagsRequest request) throws PushClientException, 
	PushServerException;
	
	public CreateTagResponse createTag (
			CreateTagRequest request) throws PushClientException, 
	PushServerException;
	
	public DeleteTagResponse deleteTag (
			DeleteTagRequest request) throws PushClientException, 
	PushServerException;
	
	public AddDevicesToTagResponse addDevicesToTag (
			AddDevicesToTagRequest request) throws PushClientException, 
	PushServerException;
	
	public DeleteDevicesFromTagResponse deleteDevicesFromTag (
			DeleteDevicesFromTagRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryDeviceNumInTagResponse queryDeviceNumInTag (
			QueryDeviceNumInTagRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryStatisticMsgResponse queryStatisticMsg (
			QueryStatisticMsgRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryStatisticTopicResponse queryStatisticTopic (
			QueryStatisticTopicRequest request) throws PushClientException, 
	PushServerException;
	
	public QueryStatisticDeviceResponse queryStatisticDevice (
			QueryStatisticDeviceRequest request) throws 
	PushClientException, PushServerException;
	
}
