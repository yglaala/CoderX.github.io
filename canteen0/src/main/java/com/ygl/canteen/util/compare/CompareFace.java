package com.ygl.canteen.util.compare;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class CompareFace {

    public static void main(String[] args) throws Exception {

        /*UrlChooser url = new UrlChooser();*/
    }

    public static ReponseData CompareByUrl(String urlA , String urlB) throws Exception {
        String appsign = Sign.appSign(1257395922L,"AKIDXyRdfWog3ILOWtG9B0d5PWWpOq6BQk3I","HN4fMDwoZ9XnuqeNwT8f6M83pP0Rjq9N","\n" +
                "ygl-ygl-ygl",30L);
        String url = "http://recognition.image.myqcloud.com/face/compare";

        APIData apiData = new APIData();
        apiData.setAppid("1257395922");
        apiData.setUrlA(urlA);
        apiData.setUrlB(urlB);

        StringEntity apiJson = new StringEntity(JSON.toJSONString(apiData));

        HttpPost httpPost = new HttpPost(url);

        httpPost.setEntity(apiJson);
        httpPost.addHeader("head","recognition.image.myqcloud.com");
        httpPost.addHeader("Content-Type","application/json");
        httpPost.addHeader("authorization",appsign);

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
        ReponseData reponseData = JSON.parseObject(result, new TypeReference<ReponseData>() {});


        if (response != null) {
            response.close();
        }
        if (client != null) {
            client.close();
        }
        return reponseData;
    }

    public static void CompareByImg(){

    }
}
