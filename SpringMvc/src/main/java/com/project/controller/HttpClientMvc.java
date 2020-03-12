package com.project.controller;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//=====================================前后端分离————前端======================================================
@RestController
@RequestMapping("userV")
public class HttpClientMvc {

    @RequestMapping("mvc")
    public String mvc(){
        //获得http客户端，对于浏览器是后端，对于后端服务器属于客户端
        HttpClient client = HttpClients.createDefault();
        //创建post请求
        HttpPost post = new HttpPost("http://localhost:8081/service/userE/ser");

        try{
            //创建响应模型
            //由客户端执行(发送)请求
            HttpResponse response = client.execute(post);
            //获取响应(返回)实体
            HttpEntity entity = response.getEntity();
            //返回实体中的字符
            String s = EntityUtils.toString(entity,"utf-8");
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            try{
                if (client!=null){
                    ((CloseableHttpClient) client).close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return "出错了，you has errors!";
    }

}
