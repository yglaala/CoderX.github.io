package com.ygl.canteen.util.request;

import com.alibaba.fastjson.JSON;
import com.ygl.canteen.util.request.common.Constants;
import com.ygl.canteen.util.request.common.Sign;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;


public class TecentFace {

    public static String CompareFaceByUrl(String urlA , String urlB) throws Exception {

        String appsign = Sign.appSign(Constants.APPID,Constants.SECRETID,Constants.SECRETKEY,Constants.BUCKETNAME,Constants.EXPIRED);

        Map<String,Object> map = new HashMap<>();
        map.put("appid",Constants.APPIDSTR);
        map.put("urlA",urlA);
        map.put("urlB",urlB);

        StringEntity apiJson = new StringEntity(JSON.toJSONString(map));

        HttpPost httpPost = new HttpPost(Constants.URL);

        httpPost.setEntity(apiJson);
        httpPost.addHeader("head","recognition.image.myqcloud.com");
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("authorization",appsign);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        if (response != null) {
            response.close();
        }
        if (client != null) {
            client.close();
        }
        return result;
    }

    public static String getInfoByUrl(String url) throws Exception {

        String appsign = Sign.appSign(Constants.APPID,Constants.SECRETID,Constants.SECRETKEY,Constants.BUCKETNAME,Constants.EXPIRED);

        Map<String,Object> map = new HashMap<>();
        map.put("appid",Constants.APPIDSTR);
        map.put("card_type",0);
        map.put("url_list",new String[]{url});
        map.put("bucket",Constants.BUCKETNAME);
        StringEntity apiJson = new StringEntity(JSON.toJSONString(map));

        HttpPost httpPost = new HttpPost("http://recognition.image.myqcloud.com/ocr/idcard");

        httpPost.setEntity(apiJson);
        httpPost.addHeader("host","recognition.image.myqcloud.com");
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("authorization",appsign);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        if (response != null) {
            response.close();
        }
        if (client != null) {
            client.close();
        }
        return result;
    }
}
