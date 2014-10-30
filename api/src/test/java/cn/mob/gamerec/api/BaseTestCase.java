package cn.mob.gamerec.api;

import cn.mob.gamerec.util.EncryptUtil;
import com.alibaba.fastjson.JSONObject;
import com.lamfire.utils.HttpClient;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/10/16
 */
public class BaseTestCase extends TestCase {

    private HttpClient httpClient;
    private JSONObject json;
    private String def_url = "http://127.0.0.1:8080";

    @Before
    public void setUp() throws Exception {
        httpClient = new HttpClient();
        httpClient.setContentType("application/json");
        httpClient.setMethod("POST");
        json = new JSONObject();
        json.put("name", "dddd");

    }

    @After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void test() {
        excute("/test/hello", json);
    }

    public void excute(String url, JSONObject jsonData) {
        try {
            httpClient.open(def_url + url);
            byte[] data = jsonData.toString().getBytes();
            data = EncryptUtil.encode(data);
            System.out.println("data====>" + new String(data));
            httpClient.post(data);
            System.out.println(httpClient.getResponseCode());
            printlnResponseResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printlnResponseResult() throws IOException {
//       String response = new String(httpClient.read());
//        System.out.println(response);
    }
}
