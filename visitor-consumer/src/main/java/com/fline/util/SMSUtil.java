package com.fline.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短信发送工具类
 */
public class SMSUtil {

    private static final String SECRET_KEY = "GNOFQPpII9bp5MmYzrxSiaVxyYyyCi";
    private static final String ACCESS_KEY = "LTAI4FkjqoLH7q5ueqqR96jP";
    private static final String FLINE_SIGN = "浙江非线数联";
    // 应急视频录制通知的短信模板编码
    private static final String TEMPLATE_CALL_VIDEO_UPLOAD = "SMS_182666152";
    // 疫情防护，健康上报的短信验证码模板
    private static final String TEMPLATE_YQFH_VERIFICATION = "SMS_183267005";
    // 重置密码短信验证码模板
    private static final String TEMPLATE_RESETPASSWORD_VERIFICATION = "SMS_183790309";
    // 重置密码的模板
    private static final String TEMPLATE_RESETPASSWORD_RANDOMPWD = "SMS_183790332";
    //找回用户名的模板
    private static final String TEMPLATE_FIND_USERNAME = "SMS_184115959";
    //找回用户名的短信验证码模板
    private static final String TEMPLATE_USERNAME_VERIFICATION = "SMS_184105954";
    //服务预警信息发送
    private static final String TEMPLATE_SERVICE_ALERT = "SMS_199808644";
    DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY, SECRET_KEY);
    IAcsClient client = new DefaultAcsClient(profile);

    public static void main(String[] args) {
        SMSUtil s = new SMSUtil();
        String phones = "15195355289";
        String name = "刘先生";
        String msg = "服雾CPU使用率过高，请尽快处理";
        s.sendAlarmInfo(phones, name,"", msg);
    }

    public String sendAlarmInfo(String phones,String name,String fdmpversion, String msg) {
        JSONObject result = new JSONObject();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            Object[] phoneSplits = phones.split(",");
            List<Object> phoneList = Arrays.asList(phoneSplits);
            JSONArray phoneArray = new JSONArray(phoneList);
            JSONArray signArray = new JSONArray();
            JSONArray paramJsonArray = new JSONArray();
            for (int i = 0; i < phoneArray.size(); i++) {
                signArray.add(FLINE_SIGN);
                JSONObject templateParamJson = new JSONObject();
                templateParamJson.put("name", name);
                templateParamJson.put("fdmpversion", fdmpversion);
                templateParamJson.put("message", msg);
                paramJsonArray.add(templateParamJson);
            }

            params.put("PhoneNumberJson", phoneArray);
            params.put("SignNameJson", signArray);
            params.put("TemplateCode", TEMPLATE_SERVICE_ALERT);
            params.put("TemplateParamJson", paramJsonArray);
            sendBatchSms(Arrays.asList(phoneSplits), params);
            result.put("data","发送成功");
            result.put("errorCode", 600);
            result.put("success", true);
        } catch (Exception e) {
            result.put("data","发送失败");
            result.put("errorCode", 500);
            result.put("success", false);
        }
        return result.toJSONString();
    }
    private void sendBatchSms(List<Object> destUsers, Map<String, Object> values) throws Exception {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendBatchSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        if (values != null && !values.isEmpty()) {
            for (Map.Entry<String, Object> kv : values.entrySet()) {
                request.putBodyParameter(kv.getKey(), kv.getValue());
            }
        }
        CommonResponse response = client.getCommonResponse(request);
        System.out.println("send success." + response.getData());
    }
}
