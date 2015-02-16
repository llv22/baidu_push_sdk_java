package com.baidu.cloud.channel.client;

import java.util.concurrent.Future;

import com.baidu.cloud.channel.exception.ChannelClientException;
import com.baidu.cloud.channel.exception.ChannelServerException;
import com.baidu.cloud.channel.model.PushBroadcastMessageRequest;
import com.baidu.cloud.channel.model.PushTagMessageRequest;
import com.baidu.cloud.channel.model.PushUnicastMessageRequest;
import com.baidu.cloud.channel.model.PushUnicastMessageResponse;
import com.baidu.cloud.channel.model.QueryBindListRequest;
import com.baidu.cloud.channel.model.QueryBindListResponse;
import com.baidu.cloud.channel.model.QueryUserTagsRequest;
import com.baidu.cloud.channel.model.QueryUserTagsResponse;
import com.baidu.cloud.channel.model.VerifyBindRequest;

public interface BaiduChannelAsync {

    public Future<PushUnicastMessageResponse> pushUnicastMessageAsync(
            final PushUnicastMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<Void> pushTagMessageAsync(final PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<Void> pushBroadcastMessageAsync(
            final PushBroadcastMessageRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<QueryBindListResponse> queryBindListAsync(
            final QueryBindListRequest request) throws ChannelClientException,
            ChannelServerException;

    public Future<Void> verifyBindAsync(final VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException;

    public Future<QueryUserTagsResponse> queryUserTagsAsync(
            final QueryUserTagsRequest request) throws ChannelClientException,
            ChannelServerException;

}
