package com.baidu.cloud.channel.client;

import java.util.Map;

import com.baidu.cloud.channel.auth.ChannelKeyPair;
import com.baidu.cloud.channel.auth.signature.ChannelSignatureDigest;
import com.baidu.cloud.channel.constants.BaiduChannelConstants;
import com.baidu.cloud.channel.exception.ChannelClientException;
import com.baidu.cloud.channel.exception.ChannelServerException;
import com.baidu.cloud.channel.model.ChannelRequest;
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
import com.baidu.cloud.channel.transform.ChannelRestRequestChecker;
import com.baidu.cloud.channel.transform.ChannelRestRequestMapper;
import com.baidu.cloud.channel.transform.ChannelRestResponseJsonUnmapper;
import com.baidu.cloud.channel.transform.utils.TransformUtilitiy;
import com.baidu.cloud.core.callback.CloudLogHttpCallBack;
import com.baidu.cloud.core.exception.CloudHttpClientException;
import com.baidu.cloud.core.httpclient.CloudHttpClient;
import com.baidu.cloud.core.log.CloudLogHandler;
import com.baidu.cloud.core.model.HttpRestResponse;

public class BaiduChannelClient implements BaiduChannel {

    private String apiKey = null;

    private String secretKey = null;

    private String host = null;

    private CloudLogHttpCallBack logHttpCallback = new CloudLogHttpCallBack();

    private ChannelRestResponseJsonUnmapper responseJsonUnmapper = new ChannelRestResponseJsonUnmapper();

    public BaiduChannelClient(ChannelKeyPair pair) {
        this(pair, BaiduChannelConstants.CHANNEL_REST_URL);
    }

    private BaiduChannelClient(ChannelKeyPair pair, String host) {
        this.apiKey = pair.getApiKey();
        this.secretKey = pair.getSecretKey();
        this.host = host;
    }

    @Override
    public QueryBindListResponse queryBindList(QueryBindListRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("query_bindlist", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryBindListResponse());
    }

    @Override
    public PushUnicastMessageResponse pushUnicastMessage(
            PushUnicastMessageRequest request) throws ChannelClientException,
            ChannelServerException {

        HttpRestResponse resp = process("push_msg", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new PushUnicastMessageResponse());

    }

    @Override
    public PushTagMessageResponse pushTagMessage(PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("push_msg", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new PushTagMessageResponse());
    }

    @Override
    public PushBroadcastMessageResponse pushBroadcastMessage(
            PushBroadcastMessageRequest request) throws ChannelClientException,
            ChannelServerException {
        HttpRestResponse resp = process("push_msg", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new PushBroadcastMessageResponse());
    }

    @Override
    public void verifyBind(VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("verify_bind", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public FetchMessageResponse fetchMessage(FetchMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("fetch_msg", request);
        return new ChannelRestResponseJsonUnmapper().unmarshall(
                resp.getHttpStatusCode(), resp.getJsonResponse(),
                new FetchMessageResponse());
    }

    @Override
    public void setTag(SetTagRequest request) throws ChannelClientException,
            ChannelServerException {
        HttpRestResponse resp = process("set_tag", request);
        new ChannelRestResponseJsonUnmapper().unmarshall(
                resp.getHttpStatusCode(), resp.getJsonResponse());
    }

    @Override
    public void deleteTag(DeleteTagRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("delete_tag", request);
        new ChannelRestResponseJsonUnmapper().unmarshall(
                resp.getHttpStatusCode(), resp.getJsonResponse());
    }

    @Override
    public FetchTagResponse fetchTag(FetchTagRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("fetch_tag", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new FetchTagResponse());
    }

    @Override
    public QueryUserTagsResponse queryUserTags(QueryUserTagsRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("query_user_tags", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryUserTagsResponse());
    }

    @Override
    public void initAppIoscert(InitAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("init_app_ioscert", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public void updateAppIoscert(UpdateAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("update_app_ioscert", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public void deleteAppIoscert(DeleteAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("delete_app_ioscert", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public QueryAppIoscertResponse queryAppIoscert(
            QueryAppIoscertRequest request) throws ChannelClientException,
            ChannelServerException {
        HttpRestResponse resp = process("query_app_ioscert", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryAppIoscertResponse());
    }

    @Override
    public QueryDeviceTypeResponse queryDeviceType(
            QueryDeviceTypeRequest request) throws ChannelClientException,
            ChannelServerException {
        HttpRestResponse resp = process("query_device_type", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryDeviceTypeResponse());
    }

    public void setChannelLogHandler(CloudLogHandler logHandler) {
        logHttpCallback.setHandler(logHandler);
    }

    // -----------------------------------------------------------

    private HttpRestResponse process(String method, ChannelRequest request)
            throws ChannelClientException, ChannelServerException {

        ChannelRestRequestChecker checker = new ChannelRestRequestChecker();
        checker.validate(request);

        ChannelRestRequestMapper mapper = new ChannelRestRequestMapper();
        Map<String, String> params = mapper.marshall(request);
        params.put("method", method);
        params.put("apikey", apiKey);

        String surl = obtainIntegrityUrl(request);

        ChannelSignatureDigest digest = new ChannelSignatureDigest();
        String sign = digest.digest("POST", surl, secretKey, params);
        params.put("sign", sign);

        CloudHttpClient client = new CloudHttpClient();
        client.addHttpCallback(logHttpCallback);

        try {
            return client.doExecutePostRequestResponse(surl, params);
        } catch (CloudHttpClientException e) {
            throw new ChannelClientException(e.getMessage());
        }
    }

    private String obtainIntegrityUrl(ChannelRequest request) {
        String resurl = host;
        if (host.startsWith("http://") || host.startsWith("https://")) {
        } else {
            resurl = "http://" + host;
        }
        if (resurl.endsWith("/")) {
            resurl = resurl + "rest/2.0/channel/";
        } else {
            resurl = resurl + "/rest/2.0/channel/";
        }
        String resourceId = TransformUtilitiy.extractResourceId(request,
                BaiduChannelConstants.CHANNEL_DEFAULT_RESOURCE_ID);
        return resurl + resourceId;
    }

}
