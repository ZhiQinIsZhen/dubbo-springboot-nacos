package com.liyz.dubbo.service.file.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.util.Asserts;

import java.util.HashMap;
import java.util.Map;

public class GezhenCreditDemo {


	public static void main(String [] args){
		//鉴权地址
		String authUrl = "http://115.236.68.59:9087/credit/auth";
		//标签查询地址
		String queryTagUrl = "http://115.236.68.59:9087/credit/standard/service";
		//用户userCode
		String appid = "ZzNvvpyUXqjDkCCZ";
		//用户秘钥
		String secret = "aecf4b2918595dfaf609f22a160e4fd0";
		//查询的服务id
		String serviceId = "To8Z19wl5DXrTUGbNJ1iC5";
		//需要查询的id值
		String idValue = "df7b7571e73f93b4c76be015c0fb3184";
		//需要查询的id类型
		String idType = "imei";
		//需要查询的标签
		String [] queryTags = new String[]{"tag_profile_019","tag_profile_003","tag_profile_004"};
		String token = getToken(appid, secret,authUrl);
		Asserts.notBlank(token,"token");
		String tagResp = getTag(token,serviceId,idValue,idType,queryTags,queryTagUrl);
		System.out.println("get tag resp:" + tagResp);
	}

	private static String getToken(String appid, String secret, String url){
		JSONObject authRequest = new JSONObject();
		long timestamp = System.currentTimeMillis();
		String Md5timestamp = DigestUtils.md5Hex(String.valueOf(timestamp));
		String sign1 = DigestUtils.md5Hex(appid + Md5timestamp);
		String sign2 = DigestUtils.md5Hex(appid + secret + Md5timestamp);
		authRequest.put("userCode", appid);
//		authRequest.put("authCode", secret);
		authRequest.put("timestamp", timestamp);
		authRequest.put("sign1", sign1);
		authRequest.put("sign2", sign2);
//		authRequest.put("platform", "gezhen_api");
		String result = HttpClientService.httpPost(url,authRequest.toJSONString(), null);
		Asserts.notBlank(result,"token result");
		System.out.println("auth resp:" + result);
		JSONObject jsonObject = JSONObject.parseObject(result);
		return jsonObject.getInteger("result") == 0 ? jsonObject.getString("data") : null;
	}

	private static String getTag(String token,String serviceId,String idValue,String idType,String [] tags, String url){
		JSONObject queryTagRequest = new JSONObject();
		Map<String,String> headers = new HashMap<String,String>(){{
			put("Authorization", "Bearer " + token);
		}};
		Asserts.notBlank(serviceId,"serviceId");
		Asserts.notBlank(idValue,"idValue");
		Asserts.notBlank(idType,"idType");
		queryTagRequest.put("idValue", idValue);
		queryTagRequest.put("serviceId", serviceId);
		queryTagRequest.put("idType", idType);
		queryTagRequest.put("queryTags", tags);
		String result = HttpClientService.httpPost(url,queryTagRequest.toJSONString(),headers);
		return result;
	}


}
