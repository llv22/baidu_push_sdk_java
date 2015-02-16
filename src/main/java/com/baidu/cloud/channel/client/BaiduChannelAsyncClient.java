package com.baidu.cloud.channel.client;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.baidu.cloud.channel.auth.ChannelKeyPair;
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

public class BaiduChannelAsyncClient extends BaiduChannelClient implements
        BaiduChannelAsync {

    private static ExecutorService execurotService = Executors
            .newFixedThreadPool(2);

    public BaiduChannelAsyncClient(ChannelKeyPair pair) {
        super(pair);
    }

    @SuppressWarnings("static-access")
    public BaiduChannelAsyncClient(ChannelKeyPair pair,
            ExecutorService execurotService) {
        super(pair);
        this.execurotService = execurotService;
    }

    @Override
    public Future<PushUnicastMessageResponse> pushUnicastMessageAsync(
            final PushUnicastMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService
                .submit(new Callable<PushUnicastMessageResponse>() {
                    @Override
                    public PushUnicastMessageResponse call() throws Exception {
                        return pushUnicastMessage(request);
                    }
                });
    }

    @Override
    public Future<Void> pushTagMessageAsync(final PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                pushTagMessage(request);
                return null;
            }
        });
    }

    @Override
    public Future<Void> pushBroadcastMessageAsync(
            final PushBroadcastMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                pushBroadcastMessage(request);
                return null;
            }
        });
    }

    @Override
    public Future<QueryBindListResponse> queryBindListAsync(
            final QueryBindListRequest request) throws ChannelClientException,
            ChannelServerException {
        return execurotService.submit(new Callable<QueryBindListResponse>() {
            @Override
            public QueryBindListResponse call() throws Exception {
                return queryBindList(request);
            }
        });
    }

    @Override
    public Future<Void> verifyBindAsync(final VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                verifyBind(request);
                return null;
            }
        });
    }

    @Override
    public Future<QueryUserTagsResponse> queryUserTagsAsync(
            final QueryUserTagsRequest request) throws ChannelClientException,
            ChannelServerException {
        return execurotService.submit(new Callable<QueryUserTagsResponse>() {
            @Override
            public QueryUserTagsResponse call() throws Exception {
                return queryUserTags(request);
            }
        });
    }

}
