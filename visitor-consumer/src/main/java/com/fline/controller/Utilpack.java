package com.fline.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具包
 */
public class Utilpack {

    /**
     * 获取微信openid
     * @param appid
     * @param key
     * @param code
     * @return
     */
    public String getopenid(String appid,String key,String code){
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        String grant_type="authorization_code";//固定值
        //使用登录凭证 code 获取 session_key 和 openid。
        Map<String,String> map=new HashMap<String,String>();
        map.put("appid", appid);//小程序的appid
        map.put("secret",key);//miniapp secret
        map.put("js_code",code);//传入得用户code
        map.put("grant_type",grant_type);
        String result="";
        try {
            result= sendGet(requestUrl,map);
//            System.out.println("获取openid结果为："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //解析返回的json数据，获得OPENID
        Map maps= JSONObject.parseObject(result);
        String openid=String.valueOf(maps.get("openid"));
//        String errmsg=String.valueOf(maps.get("errmsg"));
        String session_key=String.valueOf(maps.get("session_key"));
        if(openid!=null){
            /*在此处添加自己的逻辑代码，将openid保存在数据库，或是，使用session_key去微信服务器换取用户头像、昵称等信息。我在这里并没有用到，因此我只保存了用户的openid*/

        }
        return openid;
    }

    /**
     * 获取用户id
     * @param access_token
     * @param code
     * @return
     */
    public String getuserid(String access_token,String code){
        String requestUrl = "https://qyapi.weixin.qq.com/cgi-bin/miniprogram/jscode2session";
        //使用登录凭证 code 获取 session_key 和 userid。
        Map<String,String> map=new HashMap<String,String>();
        map.put("access_token", access_token);//企业微信应用的access_token
        map.put("js_code",code);//传入得用户code
        map.put("grant_type","authorization_code");
        String result="";
        try {
            result= sendGet(requestUrl,map);
//            System.out.println("获取openid结果为："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //解析返回的json数据，获得OPENID
        Map maps= JSONObject.parseObject(result);
        if (String.valueOf(maps.get("errcode")).equals("40029"))
            return "codeerror";
        String userid=String.valueOf(maps.get("userid"));
        String session_key=String.valueOf(maps.get("session_key"));
        if(userid!=null){
            /*在此处添加自己的逻辑代码，将openid保存在数据库，或是，使用session_key去微信服务器换取用户头像、昵称等信息。我在这里并没有用到，因此我只保存了用户的openid*/

        }
        return userid;
    }

    /**
     * 企业微信发送消息给用户
     * @param access_token
     * @param sendmsgMap
     */
    public void QYsendMessageToUser(@RequestParam String access_token,@RequestParam Map sendmsgMap){
        String requestUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
        String result="";
        try {
            result= QYsendMes(requestUrl,sendmsgMap,access_token);
            System.out.println("发送微信消息推送成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取token
     * @return
     */
    public String getAccess_token(){
        String requestUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        Map<String,String> map=new HashMap<String,String>();
        map.put("corpid","wwc567918f006a76bc");
        map.put("corpsecret","qmaqYz9aGISZrDCbf1Yo3EMyA4RxdqR7qJUhwVV0XAg");
        String result="";
        try {
            result= sendGet(requestUrl,map);
//            System.out.println("获取openid结果为："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //解析返回的json数据，获得OPENID
        Map maps= JSONObject.parseObject(result);
        String errmsg = String.valueOf(maps.get("errmsg"));
        String errcode = String.valueOf(maps.get("errcode"));
        String access_token = String.valueOf(maps.get("access_token"));
        String expires_in = String.valueOf(maps.get("expires_in"));
        if (!errcode.equals("0")){
            return "error";
        }
        return access_token;
    }

    /**
     * 发送GET请求
     *
     * @param url
     *            目的地址
     * @param parameters
     *            请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendGet(String url, Map<String, String> parameters) {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        StringBuffer sb = new StringBuffer();// 存储参数
        String params = "";// 编码之后的参数
        try {
            // 编码请求参数
            if (parameters.size() == 1) {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8"));
                }
                params = sb.toString();
            } else {
                for (String name : parameters.keySet()) {
                    sb.append(name).append("=").append(java.net.URLEncoder.encode(parameters.get(name), "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
            String full_url = url + "?" + params;
            System.out.println(full_url);
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(full_url);
            // 打开URL连接(建立了一个与服务器的tcp连接,并没有实际发送http请求！)
            URLConnection urlConnection=connURL.openConnection();
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) urlConnection;
            // 设置通用请求属性(如果已存在具有该关键字的属性，则用新值改写其值。)
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际的连接(远程对象变为可用。远程对象的头字段和内容变为可访问)
            httpConn.connect();
            // 响应头部获取
            Map<String, List<String>> headers = httpConn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 企业微信发送消息
     * @param url
     * @param parameters
     * @param access_token
     * @return
     * @throws IOException
     */
    public static String QYsendMes(String url, Map<String, Object> parameters,String access_token) throws IOException {
        String result = "";
        BufferedReader in = null;// 读取响应输入流
        String encoding="UTF-8";
        String params = JSON.toJSONString(parameters);// 编码之后的参数
        String path = url + "?access_token=" + access_token;
        byte[] data = params.getBytes(encoding);
        URL paramurl =new URL(path);
        HttpURLConnection conn = (HttpURLConnection)paramurl.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
        conn.setRequestProperty("Content-Type", "application/x-javascript; charset="+ encoding);
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setConnectTimeout(5*1000);
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        System.out.println(conn.getResponseCode()); //响应代码 200表示成功
        if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();
            // 响应头部获取
            Map<String, List<String>> headers = conn.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : headers.keySet()) {
                System.out.println(key + "\t：\t" + headers.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result += line;
            }
        }
        return result;
        }

}
