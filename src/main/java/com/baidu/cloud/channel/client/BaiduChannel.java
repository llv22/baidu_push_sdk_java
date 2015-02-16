package com.baidu.cloud.channel.client;

import com.baidu.cloud.channel.exception.ChannelClientException;
import com.baidu.cloud.channel.exception.ChannelServerException;
import com.baidu.cloud.channel.model.DeleteAppIoscertRequest;
import com.baidu.cloud.channel.model.DeleteTagRequest;
import com.baidu.cloud.channel.model.FetchMessageRequest;
import com.baidu.cloud.channel.model.FetchMessageResponse;
import com.baidu.cloud.channel.model.FetchTagRequest;
import com.baidu.cloud.channel.model.FetchTagResponse;
import com.baidu.cloud.channel.model.InitAppIoscertRequest;
import com.baidu.cloud.channel.model.PushBroadcastMessageRequest;
import com.baidu.cloud.channel.model.PushBroadcastMessageResponse;
import com.baidu.cloud.channel.model.PushTagMessageRequest;
import com.baidu.cloud.channel.model.PushTagMessageResponse;
import com.baidu.cloud.channel.model.PushUnicastMessageRequest;
import com.baidu.cloud.channel.model.PushUnicastMessageResponse;
import com.baidu.cloud.channel.model.QueryAppIoscertRequest;
import com.baidu.cloud.channel.model.QueryAppIoscertResponse;
import com.baidu.cloud.channel.model.QueryBindListRequest;
import com.baidu.cloud.channel.model.QueryBindListResponse;
import com.baidu.cloud.channel.model.QueryDeviceTypeRequest;
import com.baidu.cloud.channel.model.QueryDeviceTypeResponse;
import com.baidu.cloud.channel.model.QueryUserTagsRequest;
import com.baidu.cloud.channel.model.QueryUserTagsResponse;
import com.baidu.cloud.channel.model.SetTagRequest;
import com.baidu.cloud.channel.model.UpdateAppIoscertRequest;
import com.baidu.cloud.channel.model.VerifyBindRequest;

public interface BaiduChannel {

    public PushUnicastMessageResponse pushUnicastMessage(
            PushUnicastMessageRequest request) throws ChannelClientException,
            ChannelServerException;

    public PushTagMessageResponse pushTagMessage(PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public PushBroadcastMessageResponse pushBroadcastMessage(
            PushBroadcastMessageRequest request) throws ChannelClientException,
            ChannelServerException;

    public QueryBindListResponse queryBindList(QueryBindListRequest request)
            throws ChannelClientException, ChannelServerException;

    public void verifyBind(VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException;

    public FetchMessageResponse fetchMessage(FetchMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public void setTag(SetTagRequest request) throws ChannelClientException,
            ChannelServerException;

    public void deleteTag(DeleteTagRequest request)
            throws ChannelClientException, ChannelServerException;

    public FetchTagResponse fetchTag(FetchTagRequest request)
            throws ChannelClientException, ChannelServerException;

    public QueryUserTagsResponse queryUserTags(QueryUserTagsRequest request)
            throws ChannelClientException, ChannelServerException;

    public void initAppIoscert(InitAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public void updateAppIoscert(UpdateAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public void deleteAppIoscert(DeleteAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException;

    public QueryAppIoscertResponse queryAppIoscert(
            QueryAppIoscertRequest request) throws ChannelClientException,
            ChannelServerException;

    public QueryDeviceTypeResponse queryDeviceType(
            QueryDeviceTypeRequest request) throws ChannelClientException,
            ChannelServerException;

}
