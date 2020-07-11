package cn.coget.dora.demo;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Github 工具
 *
 * author: sin
 * time: 2020/7/10 9:45 下午
 */
public class GithubUtil {

    private static final boolean isAuthorization = false;

    private static  CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static void main(String[] args) {
        GithubUtil githubUtil = new GithubUtil();
        String authorization = githubUtil.getAuthorization();
        System.err.println("authorization: " + authorization);

        JsonObject jsonObject = new GsonBuilder().create().fromJson(authorization, JsonObject.class);

        String currentUser = githubUtil.getCurrentUser(jsonObject.get("current_user_url").getAsString());
        System.err.println("currentUser: "+ currentUser);
    }

    public String getCurrentUser(String url) {
        return sendGet(url, Maps.newHashMap());
    }

    private String getAuthorization() {
        // curl -u "cherishsince@aliyun.com:as594230" https://api.github.com
        String result = null;
        if (isAuthorization) {
            String url = "https://api.github.com";
            String base64 = Base64.encode("cherishsince@aliyun.com:as594230", Charset.forName("UTF-8"));
            System.err.println("base64: " + base64);
            result = this.sendGet(url, ImmutableMap.of("Authorization", base64));
        }
        try {
            result = FileUtil.readString("/Users/sin/github/dora/demo/src/main/java/cn/coget/dora/demo/认证.json", Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String sendGet(String url, Map<String, String> headers) {
        String result = null;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        // 设置 header 值
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        // 发送请求
        try {
            response = httpClient.execute(httpGet);
            if (200 == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
